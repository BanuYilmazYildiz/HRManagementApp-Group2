package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDto {

    private String name;
    private String secondName;
    private String surname;
    private String email; //ad.soyad@bilgeadamboost.com
    private String password;
    @Enumerated(EnumType.STRING)
    private ERole role;
    private String photo;
}
