package com.bilgeadam.controller;

import com.bilgeadam.dto.request.EmployeeCreateRequestDto;
import com.bilgeadam.dto.request.EmployeeUpdateRequestDto;
import com.bilgeadam.dto.response.EmployeeFindByUserIdDetailResponseDto;
import com.bilgeadam.dto.response.EmployeeFindByUserIdResponseDto;
import com.bilgeadam.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.bilgeadam.constants.RestApi.*;
import static com.bilgeadam.constants.RestApi.FINDBYIDDETAIL;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLYOYEE)
public class EmployeeController {

    private final EmployeeService employeeService;

    @PutMapping(UPDATE_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateUser( @RequestBody EmployeeUpdateRequestDto dto){
        return ResponseEntity.ok(employeeService.updateUser( dto));
    }

    @GetMapping(FINDBYIDDETAIL)
    @CrossOrigin("*")
    public ResponseEntity<EmployeeFindByUserIdDetailResponseDto> findById(@PathVariable Long userId){
        return ResponseEntity.ok(employeeService.findOptionalByIdDetail(userId));
    }

    @GetMapping(FINDBYID2)
    @CrossOrigin("*")
    public ResponseEntity<EmployeeFindByUserIdDetailResponseDto> findByUserDto(@PathVariable Long userId){
        return ResponseEntity.ok(employeeService.findEmployee2(userId));
    }

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createEmployee(@RequestBody EmployeeCreateRequestDto dto){
        return ResponseEntity.ok(employeeService.createUser(dto));
    }

   @CrossOrigin("*")
    @PostMapping(IMAGE_UPLOAD)
    public ResponseEntity<String> updateImage(@RequestParam("file") MultipartFile file, @RequestParam("token") String token) throws IOException {
        return ResponseEntity.ok(employeeService.updateImage(file,token));
    }


}
