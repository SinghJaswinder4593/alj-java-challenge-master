package jp.co.axa.apidemo.controllers;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.EmployeeDTO;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;


@Controller
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //To set the mapping of the url to Homepage
    @GetMapping("/")
    public String home() {
        return "homePage";
    }

    //To set the mapping of the url to add_emp page
    @GetMapping("/addEmp")
    public String addEmpForm() {
        return "add_emp";
    }

    //To retrieve the list of employees from the database
    @GetMapping("/employees")
    public String getEmployees(Model activeEmployeeList) {
        List<Employee> employees = employeeService.retrieveEmployees();
        activeEmployeeList.addAttribute("emp", employees);
        return "index";
    }

    //To get the employee details from the database by employeeId
    @GetMapping("/employees/{employeeId}")
    public String getEmployee(@PathVariable Long employeeId, Model m) {

        Employee e = employeeService.getEmployee(employeeId);
        m.addAttribute("emp", e);
        return "updateEmployeeDetails";
    }

    //To save the employee details into the database
    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute Employee employee, HttpSession session) {
        employeeService.saveEmployee(employee);
        session.setAttribute("msg", "Employee Added Successfully");
        return "redirect:/api/v1/employees/";
    }

    //To delete the employee details into the database
    @GetMapping("/employeeDelete/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") Long employeeId, HttpSession session) {
        employeeService.deleteEmployee(employeeId);
        session.setAttribute("msg", "Employee Deleted Successfully");
        return "redirect:/api/v1/employees/";
    }

    //To update the employee details from the database by employeeId
    @PostMapping("/employees/{employeeId}")
    public String updateEmployee(@ModelAttribute Employee employee, HttpSession session) {
        employeeService.updateEmployee(employee);
        session.setAttribute("msg", "Employee Details updated Successfully");
        return "redirect:/api/v1/employees/";
    }


    //To get the employee details from the uploaded csv sheet using uploadCSVFile
    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model, HttpSession session) {
        String fileType = "text/csv";
        // validate file
        if (fileType.equals(file.getContentType())) {
            if (file.isEmpty()) {
                session.setAttribute("msg", "Please select a valid .CSV file");
                return "redirect:/api/v1/addEmp";
            } else {

                // parse CSV file to create a list of `User` objects
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                    // create csv bean reader
                    CsvToBean<EmployeeDTO> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(EmployeeDTO.class)
                            .withSeparator(',')
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

                    // convert `CsvToBean` object to list of users
                    List<EmployeeDTO> employee = csvToBean.parse();
                    Employee employeeDetails = new Employee();

                    // save users list on model
                    for (EmployeeDTO employeeDTO : employee) {
                        // To ADD the employees in bulk
                        if (employeeDTO.getStatus().equals("Add")) {

                            employeeDetails.setId(employeeDTO.getId());
                            employeeDetails.setDepartment(employeeDTO.getDepartment());
                            employeeDetails.setName(employeeDTO.getName());
                            employeeDetails.setSalary(employeeDTO.getSalary());

                            model.addAttribute("Employee", employee);
                            model.addAttribute("status", true);
                            employeeService.saveEmployee(employeeDetails);
                            session.setAttribute("msg", "Employee Details added Successfully");

                            // To UPDATE the employees in bulk
                        } else if (employeeDTO.getStatus().equals("Update")) {
                            employeeDetails.setId(employeeDTO.getId());
                            employeeDetails.setDepartment(employeeDTO.getDepartment());
                            employeeDetails.setName(employeeDTO.getName());
                            employeeDetails.setSalary(employeeDTO.getSalary());

                            model.addAttribute("Employee", employee);
                            model.addAttribute("status", true);
                            employeeService.updateEmployee(employeeDetails);
                            session.setAttribute("msg", "Employee Details Updated Successfully");

                            // To DELETE the employees in bulk
                        } else if (employeeDTO.getStatus().equals("Delete")) {
                            long empId = employeeDTO.getId();
                            model.addAttribute("Employee", employee);
                            model.addAttribute("status", true);
                            employeeService.deleteEmployee(empId);
                            session.setAttribute("msg", "Employee Details Deleted Successfully");

                        }
                    }
                } catch (Exception ex) {
                    model.addAttribute("message", "An error occurred while processing the CSV file.");
                    model.addAttribute("status", false);
                }

            }
        } else {
            session.setAttribute("msg", "Please select a valid .CSV file");
            return "redirect:/api/v1/addEmp";
        }

        return "redirect:/api/v1/employees/";
    }

    // To Export the Active Employee List in .CSV format
    @GetMapping("/csvexport")
    public void exportCSV(HttpServletResponse response) throws Exception{

        String fileName = "Employeedata.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\"" + fileName + "\"");


        StatefulBeanToCsv<Employee> writer = new StatefulBeanToCsvBuilder<Employee>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false).build();
        writer.write(employeeService.retrieveEmployees());

    }

}


