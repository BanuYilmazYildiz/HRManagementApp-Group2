package com.bilgeadam.controller;


import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.*;

import com.bilgeadam.dto.request.CreateCompanyRequestDto;
import com.bilgeadam.dto.request.CreateManagerRequestDto;
import com.bilgeadam.dto.request.ImageUploadRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.dto.response.ManagerFindByUserIdDetailResponseDto;
import com.bilgeadam.repository.entity.Company;
import com.bilgeadam.repository.entity.ExpenseForManager;
import com.bilgeadam.service.CompanyService;

import com.bilgeadam.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Permission;
import java.util.List;

import static com.bilgeadam.constants.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(MANAGER)
@CrossOrigin("*")
public class ManagerController {
    private final ManagerService managerService;
    private final CompanyService companyService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createManager(@RequestBody @Valid CreateManagerRequestDto dto){
        return ResponseEntity.ok(managerService.createManager(dto));
    }
    @GetMapping(FINDBYID2)
    @CrossOrigin("*")
    public ResponseEntity<ManagerFindByUserIdDetailResponseDto> findByUserDto(@PathVariable Long userId){
        return ResponseEntity.ok(managerService.findManager(userId));
    }

    @CrossOrigin("*")
    @PostMapping(value = IMAGE_UPLOAD,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> updateImage(@RequestBody @Valid @ModelAttribute ImageUploadRequestDto dto)  {
        return ResponseEntity.ok(managerService.updateImage(dto));
    }

    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateUser( @RequestBody UpdateRequestDto dto){
        return ResponseEntity.ok(managerService.updateUser(dto));
    }

    @GetMapping(FIND_ALL_EXPENSE_FOR_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<List<ExpenseListManagerResponseDto>> findAllExpenseManager(String token){
        return ResponseEntity.ok(managerService.findAllExpenseManager(token));
    }


    @PutMapping(EXPENSE_APPROVE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateStatusExpense(@RequestBody UpdateExpenseStatusRequestDto dto){
        return ResponseEntity.ok(managerService.updateStatusExpense(dto));
    }

    @PutMapping(ADVANCE_APPROVE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateStatusAdvance(@RequestBody UpdateAdvanceStatusRequestDto dto){
        return ResponseEntity.ok(managerService.updateStatusAdvance(dto));
    }

    @GetMapping(FIND_ALL_ADVANCE_FOR_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<List<AdvanceListManagerResponseDto>> findAllAdvanceManager(String token){
        return ResponseEntity.ok(managerService.findAllAdvanceManager(token));
    }

    @GetMapping(FIND_ALL_PERMISSION_FOR_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<List<PermissionListManagerResponseDto>> findAllPermissionManager(String token){
        return ResponseEntity.ok(managerService.findAllPermissionManager(token));
    }

    @PutMapping(PERMISSION_APPROVE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateStatusPermission(@RequestBody UpdatePermissionStatusRequestDto dto){
        return ResponseEntity.ok(managerService.updateStatusPermission(dto));
    }
    @PostMapping(COMPANY_CREATE)
    public ResponseEntity<Boolean> createCompany(@RequestBody @Valid CreateCompanyRequestDto dto) {
        return ResponseEntity.ok(companyService.createCompany(dto));
    }
    @PutMapping(COMPANY_UPDATE)
    public ResponseEntity<Company> updateCompany(@RequestBody @Valid UpdateCompanyResponseDto dto){
        return ResponseEntity.ok(companyService.updateCompany(dto));
    }
    @DeleteMapping(COMPANY_DELETEBYID)
    public ResponseEntity<Boolean> deleteCompany(@RequestParam Long id){
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }
    @GetMapping(FINDALL_COMPANY)
    public ResponseEntity<List<Company>> findAll() {
        return ResponseEntity.ok(companyService.findAll());
    }
    @GetMapping(COMPANY_DETAIL)
    public ResponseEntity<Company> detailInfo(@RequestParam String id) {
        return ResponseEntity.ok(companyService.getDetailInfo(id));
    }


    @GetMapping(FIND_ALL_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<List<ManagerListResponseDto>> findAllManager(){
        return ResponseEntity.ok(managerService.findAllManager());
    }

    @GetMapping(FIND_ALL_ADMIN)
    @CrossOrigin("*")
    public ResponseEntity<List<AdminListResponseDto>> findAllAdmin(){
        return ResponseEntity.ok(managerService.findAllAdmin());
    }
}
