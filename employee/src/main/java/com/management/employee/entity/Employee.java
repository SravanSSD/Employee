package com.management.employee.entity;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import jakarta.persistence.CollectionTable;
//import jakarta.persistence.Column;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ElementCollection;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//
//@Entity
//public class Employee {
//
//    @Id
//    @NotNull(message = "Employee ID cannot be null")
//    private String employeeId;
//
//    @NotNull(message = "First name cannot be null")
//    @Size(min = 1, message = "First name cannot be empty")
//    private String firstName;
//
//    @NotNull(message = "Last name cannot be null")
//    @Size(min = 1, message = "Last name cannot be empty")
//    private String lastName;
//
//    @NotNull(message = "Email cannot be null")
//    @Email(message = "Email should be valid")
//    private String email;
//
//    @ElementCollection
//    @CollectionTable(
//        name = "employee_phone_numbers", 
//        joinColumns = @JoinColumn(name = "employee_id")
//    )
//    @Column(name = "phone_number")
//    private List<String> phoneNumbers;
//    @NotNull(message = "Phone number cannot be null")
//    private String phoneNumber;  // Main phone number (optional)
//
//
//    @NotNull(message = "Date of Joining cannot be null")
//    private LocalDate doj;  // Date of Joining
//
//    @NotNull(message = "Salary cannot be null")
//    private Double salary;
//
//    // Getters and Setters...
//    
//
//    public List<String> getPhoneNumbers() {
//        return phoneNumbers;
//    }
//
//    public String getEmployeeId() {
//		return employeeId;
//	}
//
//	public void setEmployeeId(String employeeId) {
//		this.employeeId = employeeId;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public LocalDate getDoj() {
//		return doj;
//	}
//
//	public void setDoj(LocalDate doj) {
//		this.doj = doj;
//	}
//
//	public Double getSalary() {
//		return salary;
//	}
//
//	public void setSalary(Double salary) {
//		this.salary = salary;
//	}
//
//	public void setPhoneNumbers(List<String> phoneNumbers) {
//        this.phoneNumbers = phoneNumbers;
//    }
//    
//    
//}

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList; // Make sure to import ArrayList
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Employee {

    @Id
    @NotNull(message = "Employee ID cannot be null")
    private String employeeId;

    @NotNull(message = "First name cannot be null")
    @Size(min = 1, message = "First name cannot be empty")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, message = "Last name cannot be empty")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    // Initialize the phoneNumbers list to avoid null issues
    @ElementCollection
    @CollectionTable(
        name = "employee_phone_numbers", 
        joinColumns = @JoinColumn(name = "employee_id")
    )
    @Column(name = "phone_number", nullable = true) 
    @UniqueElements// Allowing NULL for phone number
    private List<String> phoneNumbers = new ArrayList<>();  // Initialize to an empty list

    @NotNull(message = "Date of Joining cannot be null")
    private LocalDate doj;  // Date of Joining

    @NotNull(message = "Salary cannot be null")
    private Double salary;

    // Getters and Setters

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
