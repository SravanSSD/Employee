 package com.management.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.employee.entity.Employee;
import com.management.employee.entity.EmployeeTaxDetails;
import com.management.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
 @RequestMapping("/api/employees")
 public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(" "));
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        try {
            employeeService.saveEmployee(employee);
            return ResponseEntity.ok("Employee added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
     // Endpoint to calculate tax for employees
     @GetMapping("/tax-deductions")
     public ResponseEntity<List<EmployeeTaxDetails>> calculateTaxDeductions() {
         List<EmployeeTaxDetails> taxDetails = employeeService.calculateTaxDeductions();
         return ResponseEntity.ok(taxDetails);
     }
 }
