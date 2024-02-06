package com.bilgeadam.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDto {
    @Email
    @NotEmpty(message = "E-mail kısmı boş olamaz")
    private String email;
    @NotEmpty(message = "Sifre bos bırakılamaz")
    private String password;
}
