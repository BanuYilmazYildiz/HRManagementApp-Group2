package com.bilgeadam.controller;

import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.EmployeeFindByUserIdDetailResponseDto;

import com.bilgeadam.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.bilgeadam.constants.RestApi.*;
import static com.bilgeadam.constants.RestApi.FINDBYIDDETAIL;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLYOYEE)
@CrossOrigin("*")
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
   @PostMapping(value = IMAGE_UPLOAD,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> updateImage(@RequestBody @Valid @ModelAttribute ImageUploadRequestDto dto)  {
        return ResponseEntity.ok(employeeService.updateImage(dto));
    }

    @PostMapping(PERMISSION_CREATE)
    @CrossOrigin("*")
    public ResponseEntity<?> createPermission(@RequestBody CreatePermissionRequestDto dto){
        return ResponseEntity.ok(employeeService.createPermission(dto));
    }

//    @CrossOrigin("*")
//    @PutMapping(PERMISSION_APPROVE)
//    public ResponseEntity<Boolean> updateStatusPermission(@RequestBody UpdateStatusRequestDto dto){
//        return ResponseEntity.ok(employeeService.updateStatusPermission(dto));
//    }


    @PostMapping(EXPENSE_CREATE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createExpense(@RequestBody CreateExpenseRequestDto dto){
        return ResponseEntity.ok(employeeService.createExpense(dto));
    }


}
