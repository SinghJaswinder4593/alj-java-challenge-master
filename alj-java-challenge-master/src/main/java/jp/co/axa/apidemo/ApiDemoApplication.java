package jp.co.axa.apidemo;


import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.services.EmployeeServiceImpl;
import lombok.Builder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Optional;


@EnableSwagger2
@SpringBootApplication
@EntityScan
public class ApiDemoApplication {


	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ApiDemoApplication.class, args);


	}
}
