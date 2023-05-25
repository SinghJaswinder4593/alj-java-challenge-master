### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.
> 
>
  - Name of the service : Welcome to Employee management System.
  - Platforms used : Java, Springboot, Bootstrap, Thymeleaf. 
  - Features : Add, Update, Delete and Read Employee details. 
  - Additional features : Login using Admin credentials.
      Displays the list of employees on the Employees page.
      Bulk Addition, Updation and Deletion by uploading the csv file directly. 
      Generate/download the active Employee list directly from the database

### Instructions

- Clone the project from the repository.
- Start the application and launch the url: "http://localhost:8080/api/v1/".
  - Login with the below ID and PASSWORD:
 ###  ID: Admin ; PASSWORD: password    (It will take you to the HomePage)
- You can click on ADD Employee to ADD the Employee one by one and save the details into the .h2 database.
- You can delete/Update the Employee details from the "http://localhost:8080/api/v1/employees/" page
- You can generate the active Employees List from the "Generate Active Employee Report" button. (It will generate a list of all active employees from the database)

## Testdata

-You can find the Testdata in the test/testdata folder having multiple scenarios to test the application

### Future enhancements which can be done

- Authentication of Username and Password from the database using Password Encoder and BCrypt.
- Designing the admin and user authentication service using JWT token.
- Restructuring the Active Employee report to show the quaterly employee budget report.
- Sending onboarding emails to newly registered employees.
- Enhancing the UI.
- Adding more features and employee details to the database.

#### Restrictions
- use java 8

#### Your experience in Java

Please let us know more about your Java experience in a few sentences. For example:

- I have 6 years experience in Java and I started to use Spring Boot from past 2 years

