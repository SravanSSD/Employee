package com.management.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.employee.entity.Employee;
import com.management.employee.entity.EmployeeTaxDetails;
import com.management.employee.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee) {
        // Add validation logic if required
        if (employee.getEmployeeId() == null || employee.getFirstName() == null || employee.getLastName() == null || employee.getEmail() == null) {
            throw new IllegalArgumentException("All fields are mandatory.");
        }
        // Save employee to database
        employeeRepository.save(employee);
    }

    public List<EmployeeTaxDetails> calculateTaxDeductions() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeTaxDetails> taxDetailsList = new ArrayList<>();

        for (Employee employee : employees) {
            double yearlySalary = calculateYearlySalary(employee);
            double tax = calculateTax(yearlySalary);
            double cess = calculateCess(yearlySalary);

            taxDetailsList.add(new EmployeeTaxDetails(
                    employee.getEmployeeId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    yearlySalary,
                    tax,
                    cess
            ));
        }

        return taxDetailsList;
    }

    private double calculateYearlySalary(Employee employee) {
        LocalDate currentDate = LocalDate.now();
        LocalDate joinDate =employee.getDoj();
        int monthsWorked = currentDate.getMonthValue() - joinDate.getMonthValue();

        // If the employee joined this year and before current month
        if (joinDate.getMonthValue() > currentDate.getMonthValue()) {
            // If the join month is greater than current month, the employee hasn't worked a full year yet
            monthsWorked += 12; 
        }

        double totalSalary = 0;

        // Add salary for full months
        totalSalary += employee.getSalary() * (monthsWorked - 1);

        // Handle the partial month salary (the first month)
        int daysInJoiningMonth = joinDate.lengthOfMonth();
        int daysWorkedInJoiningMonth = daysInJoiningMonth - joinDate.getDayOfMonth() + 1;
        
        double proratedSalaryForJoiningMonth = (employee.getSalary() / daysInJoiningMonth) * daysWorkedInJoiningMonth;

        totalSalary += proratedSalaryForJoiningMonth;

        return totalSalary;
    }
    private double calculateTax(double yearlySalary) {
        double tax = 0;

        if (yearlySalary <= 250000) {
            return tax;
        } else if (yearlySalary <= 500000) {
            tax = (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
            tax = 12500 + (yearlySalary - 500000) * 0.1;
        } else {
            tax = 37500 + (yearlySalary - 1000000) * 0.2;
        }

        return tax;
    }

    private double calculateCess(double yearlySalary) {
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02;
        }
        return 0;
    }
}
