package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateManagerRequestDto;
import com.bilgeadam.dto.reponse.ManagerFindByUserIdDetailResponseDto;
import com.bilgeadam.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
