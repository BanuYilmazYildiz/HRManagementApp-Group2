package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateManagerRequestDto {

    private Long userId;
    private String name;
    private String secondName;
    private String surname;
    private String photo;
    private LocalDate birthday;
    private String birthplace;
    private String tcNo;
    private LocalDate startDateToWork;
    private LocalDate resignationDate; //işten çıkış tarihi
    private String profession; //meslek
    private String department;
    private String company;
    private String personalEmail; //ad.soyad@bilgeadamboost.com
    private String address;
    private String phone;
    private Double salary;
    private ERole role;
}
