package com.bilgeadam.service;

import com.bilgeadam.repository.EmployeeRepository;
import com.bilgeadam.repository.entity.Employee;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends ServiceManager<Employee,String> {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.employeeRepository=employeeRepository;
    }
}
