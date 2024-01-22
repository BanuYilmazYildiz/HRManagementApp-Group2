package com.bilgeadam.repository.entity;

import com.bilgeadam.utility.enums.EActivation;
import com.bilgeadam.utility.enums.ERole;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Manager extends BaseEntity{
    @Id
    private String id;

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

    @Builder.Default
    private EActivation activation = EActivation.WORKING;
    private String profession;
    private String department;
    private String company;
    private String email;
    private String address;
    private String phone;
    private Double salary;
    private ERole role;
}
