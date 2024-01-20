package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeCreateRequestDto {

    private Long userId;
    private String name;
    private String secondName;
    private String surname;
    private String email; //ad.soyad@bilgeadamboost.com
    private String password;
    private ERole role;
    private String photo;
}
