package com.management.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages= {"com.management.employee"})
@EnableJpaRepositories(basePackages= {"com.management.employee.repository"})
@EntityScan(basePackages= {"com.management.employee.entity"})
@EnableAutoConfiguration
@SpringBootApplication
public class EmployeeApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
