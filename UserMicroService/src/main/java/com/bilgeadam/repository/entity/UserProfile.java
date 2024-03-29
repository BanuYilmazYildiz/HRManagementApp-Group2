package com.bilgeadam.repository.entity;

import com.bilgeadam.utility.enums.ERole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String secondName;
    private String surname;
    private String email; //ad.soyad@bilgeadamboost.com
    private String password;
    private ERole role;
    private String tcNo;
    private String company;
    @Builder.Default
    private LocalDate startDateToWork=LocalDate.now();
    private LocalDate resignationDate; //işten çıkış tarihi
    private String photo;

    private LocalDate birthday;
    private String birthplace;
    private String profession; //meslek
    private String department;
    private String address;
    private String phone;
    private Double salary;
    private String personalEmail;
    private String activationCode;






}
