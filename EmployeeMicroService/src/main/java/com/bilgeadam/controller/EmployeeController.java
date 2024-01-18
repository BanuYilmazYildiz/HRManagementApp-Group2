package com.bilgeadam.controller;

import com.bilgeadam.dto.request.EmployeeUpdateRequestDto;
import com.bilgeadam.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bilgeadam.constants.RestApi.EMPLYOYEE;
import static com.bilgeadam.constants.RestApi.UPDATE_EMPLOYEE;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLYOYEE)
public class EmployeeController {

    private final EmployeeService employeeService;

    @PutMapping(UPDATE_EMPLOYEE)
    public ResponseEntity<Boolean> updateUser( @RequestBody EmployeeUpdateRequestDto dto){
        return ResponseEntity.ok(employeeService.updateUser( dto));
    }

}
