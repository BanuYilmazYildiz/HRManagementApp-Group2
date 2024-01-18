package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeUpdateRequestDto {


    private String name;
    private String secondName;
    private String surname;
    private LocalDate birthday;
    private String birthplace;
    private LocalDate startDateToWork;
    private LocalDate resignationDate; //işten çıkış tarihi
    private String profession; //meslek
    private String department;
    private String company;
    private String address;
    private String phone;
    private Double salary;
    private String token;
    private String password;


}
