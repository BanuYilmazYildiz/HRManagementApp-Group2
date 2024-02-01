package com.bilgeadam.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordRequestDto {
    private String token;
    @Size(min = 8, max = 32, message = "Şifre min 8 mac 32 karakter olmalı")
    private String password;
    @Size(min = 8, max = 32, message = "Şifre min 8 mac 32 karakter olmalı")
    private String passwordConfirmation;
}
