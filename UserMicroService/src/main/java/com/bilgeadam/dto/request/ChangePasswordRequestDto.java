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

            @Pattern(regexp = ".*[0-9].*", message = "Şifre en az bir rakam içermelidir.")
            @Pattern(regexp = ".*[a-z].*", message = "Şifre en az bir küçük harf içermelidir.")
            @Pattern(regexp = ".*[A-Z].*", message = "Şifre en az bir büyük harf içermelidir.")
            @Pattern(regexp = ".*[@#$%^&-+=().!].*", message = "Şifre en az bir özel karakter içermelidir.")
            @Pattern(regexp = "\\S+", message = "Şifre boşluk içeremez.")
    @Size(min = 8, max = 32, message = "Şifre en az 8 en fazla 32 karakter içermelidir.")
    private String password;

            @Pattern(regexp = ".*[0-9].*", message = "Şifre en az bir rakam içermelidir.")
            @Pattern(regexp = ".*[a-z].*", message = "Şifre en az bir küçük harf içermelidir.")
            @Pattern(regexp = ".*[A-Z].*", message = "Şifre en az bir büyük harf içermelidir.")
            @Pattern(regexp = ".*[@#$%^&-+=().!].*", message = "Şifre en az bir özel karakter içermelidir.")
            @Pattern(regexp = "\\S+", message = "Şifre boşluk içeremez.")

    @Size(min = 8, max = 32, message = "Şifre en az 8 en fazla 32 karakter içermelidir.")
    private String passwordConfirmation;
}
