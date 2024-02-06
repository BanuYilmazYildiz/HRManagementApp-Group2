package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Company extends BaseEntity{
    @Id
    private String id;
    private String companyName;
    private String title;
    private String mersisNumber;
    private String phone;
    private String address;
    private String email;
    private LocalDate foundationYear;//firma kuruluş tarihi
    private LocalDate contractStartDate;
    private LocalDate contractFinishDate;
    private EStatus status;
}
