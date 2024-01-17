package com.bilgeadam.controller;

import com.bilgeadam.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bilgeadam.constants.RestApi.EMPLYOYEE;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLYOYEE)
public class EmployeeController {

    private final EmployeeService employeeService;
    

}
