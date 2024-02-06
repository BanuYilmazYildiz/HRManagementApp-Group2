package com.bilgeadam.dto.request;

import jakarta.validation.constraints.Pattern;
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
    @Pattern.List({
            @Pattern(regexp = ".[0-9].", message = "Şifre en az bir rakam içermelidir."),
            @Pattern(regexp = ".[a-z].", message = "Şifre en az bir küçük harf içermelidir."),
            @Pattern(regexp = ".[A-Z].", message = "Şifre en az bir büyük harf içermelidir."),
            @Pattern(regexp = ".[@#$%^&-+=()].", message = "Şifre en az bir özel karakter içermelidir."),
            @Pattern(regexp = "\\S+", message = "Şifre boşluk içeremez."),
    })
    private String password;
    @Pattern.List({
            @Pattern(regexp = ".[0-9].", message = "Şifre en az bir rakam içermelidir."),
            @Pattern(regexp = ".[a-z].", message = "Şifre en az bir küçük harf içermelidir."),
            @Pattern(regexp = ".[A-Z].", message = "Şifre en az bir büyük harf içermelidir."),
            @Pattern(regexp = ".[@#$%^&-+=()].", message = "Şifre en az bir özel karakter içermelidir."),
            @Pattern(regexp = "\\S+", message = "Şifre boşluk içeremez."),
    })
    private String passwordConfirmation;
}
