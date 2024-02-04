package com.bilgeadam.controller;


import com.bilgeadam.dto.request.*;


import com.bilgeadam.dto.response.*;

import com.bilgeadam.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.bilgeadam.constants.RestApi.*;
import static com.bilgeadam.constants.RestApi.ADVANCE_APPROVE;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLYOYEE)
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService employeeService;


    @PutMapping(UPDATE_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateUser( @RequestBody @Valid EmployeeUpdateRequestDto dto){
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
    public ResponseEntity<Boolean> createEmployee(@RequestBody @Valid EmployeeCreateRequestDto dto){
        return ResponseEntity.ok(employeeService.createUser(dto));
    }

   @CrossOrigin("*")
   @PostMapping(value = IMAGE_UPLOAD,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> updateImage(@RequestBody @Valid @ModelAttribute ImageUploadRequestDto dto)  {
        return ResponseEntity.ok(employeeService.updateImage(dto));
    }

    @PostMapping(PERMISSION_CREATE)
    @CrossOrigin("*")
    public ResponseEntity<?> createPermission(@RequestBody @Valid CreatePermissionRequestDto dto){
        return ResponseEntity.ok(employeeService.createPermission(dto));
    }

    @CrossOrigin("*")
    @GetMapping(FIND_ALL_PERMISSION)
    public ResponseEntity<List<PermissionResponseDto>> findAllPermission(String token){
        return ResponseEntity.ok(employeeService.findAllPermission(token));
    }

    @CrossOrigin("*")
    @PutMapping(PERMISSION_APPROVE)
    public ResponseEntity<Boolean> updateStatusPermission(@RequestBody @Valid UpdateStatusRequestDto dto){
        return ResponseEntity.ok(employeeService.updateStatusPermission(dto));
    }


    @PostMapping(EXPENSE_CREATE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createExpense(@RequestBody @Valid CreateExpenseRequestDto dto){
        return ResponseEntity.ok(employeeService.createExpense(dto));
    }



    @CrossOrigin("*")
    @GetMapping(FIND_ALL_EXPENSE)
    public ResponseEntity<List<ExpenseResponseDto>> findAllExpense(String token){
        return ResponseEntity.ok(employeeService.findAllExpensestoken(token));
    }
    @PostMapping(CREATE_ADVANCE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createAdvance(@RequestBody @Valid CreateAdvanceRequestDto dto){
        return ResponseEntity.ok(employeeService.createAdvance(dto));
    }

    @CrossOrigin("*")
    @GetMapping(FIND_ALL_ADVANCE)
    public ResponseEntity<List<AdvanceResponseDto>> findAllAdvance(String token){
        return ResponseEntity.ok(employeeService.findAllAdvance(token));
    }


    @CrossOrigin("*")
    @PutMapping(ADVANCE_APPROVE)
    public ResponseEntity<Boolean> updateStatusAdvance(@RequestBody @Valid UpdateStatusRequestDto dto){
        return ResponseEntity.ok(employeeService.updateStatusAdvance(dto));
    }

    @CrossOrigin("*")
    @GetMapping(FIND_ALL_EMPLOYEE)
    public ResponseEntity<List<FindAllEmployeeResponseDto>> findAllEmployee(String company){
        return ResponseEntity.ok(employeeService.findAllEmployee(company));
    }





}
