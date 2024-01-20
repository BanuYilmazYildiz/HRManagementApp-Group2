package com.bilgeadam.controller;

import com.bilgeadam.dto.request.EmployeeCreateRequestDto;
import com.bilgeadam.dto.request.EmployeeUpdateRequestDto;
import com.bilgeadam.dto.response.EmployeeFindByUserIdResponseDto;
import com.bilgeadam.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bilgeadam.constants.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLYOYEE)
public class EmployeeController {

    private final EmployeeService employeeService;

    @PutMapping(UPDATE_EMPLOYEE)
    public ResponseEntity<Boolean> updateUser( @RequestBody EmployeeUpdateRequestDto dto){
        return ResponseEntity.ok(employeeService.updateUser( dto));
    }

    @GetMapping(FINDBYID)
    @CrossOrigin("*")
    public ResponseEntity<EmployeeFindByUserIdResponseDto> findById(@RequestParam String token){
        return ResponseEntity.ok(employeeService.findOptionalById(token));
    }
    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createEmployee(@RequestBody EmployeeCreateRequestDto dto){
        return ResponseEntity.ok(employeeService.createUser(dto));
    }


}
