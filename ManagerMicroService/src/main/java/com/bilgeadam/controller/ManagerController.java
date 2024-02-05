package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateManagerRequestDto;
import com.bilgeadam.dto.request.ImageUploadRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.dto.response.ManagerFindByUserIdDetailResponseDto;
import com.bilgeadam.repository.entity.ExpenseForManager;
import com.bilgeadam.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constants.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(MANAGER)
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createManager(@RequestBody CreateManagerRequestDto dto){
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
    public ResponseEntity<List<ExpenseForManager>> findAllExpense( ){
        return ResponseEntity.ok(managerService.findAllExpense());
    }

}
