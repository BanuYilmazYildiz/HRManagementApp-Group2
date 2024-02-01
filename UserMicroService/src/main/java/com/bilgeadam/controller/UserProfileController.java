package com.bilgeadam.controller;

import com.bilgeadam.dto.request.ChangePasswordRequestDto;
import com.bilgeadam.dto.request.PasswordResetRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.PasswordResetResponseDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bilgeadam.constants.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
@CrossOrigin("*")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(userProfileService.login(dto));
    }
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto>  register (@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(userProfileService.register(dto));
    }

//    @PostMapping(REGISTER+"2")
//    public ResponseEntity<RegisterResponseDto>  registerWithRabbitMQ (@RequestBody @Valid RegisterRequestDto dto){
//        return ResponseEntity.ok(userProfileService.registerWithRabbitMQ(dto));
//    }
    // sifremi unuttum endpointi

    @PostMapping(PASSWORDRESET)
    public ResponseEntity<PasswordResetResponseDto> passwordReset(@RequestBody @Valid PasswordResetRequestDto dto){
        return ResponseEntity.ok(userProfileService.passwordReset(dto));
    }

    @PostMapping(CHANGEPASSWORD)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> changePassword(@RequestBody @Valid ChangePasswordRequestDto dto){
        return ResponseEntity.ok(userProfileService.changePassword(dto));
    }

}
