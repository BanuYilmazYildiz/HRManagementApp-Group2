package com.bilgeadam.controller;

import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.AdvanceListManagerResponseDto;
import com.bilgeadam.dto.response.ExpenseListManagerResponseDto;
import com.bilgeadam.dto.response.ManagerFindByUserIdDetailResponseDto;
import com.bilgeadam.dto.response.PermissionListManagerResponseDto;
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

}
