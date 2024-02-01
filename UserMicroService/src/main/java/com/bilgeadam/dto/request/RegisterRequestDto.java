package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDto {

    @NotEmpty(message = "Ad alanını boş bırakmayınız.")
    private String name;
    private String secondName;
    @NotEmpty(message = "Soyadı alanını boş bırakmayınız.")
    private String surname;
    private String photo;
    private LocalDate birthday;
    private String birthplace;
    private String tcNo;
    private LocalDate startDateToWork;
    private String profession;
    private String department;
    private String company;
    private String personalEmail;
    private String address;
    private String phone;
    private Double salary;
    @Enumerated(EnumType.STRING)
    private ERole role;

}
