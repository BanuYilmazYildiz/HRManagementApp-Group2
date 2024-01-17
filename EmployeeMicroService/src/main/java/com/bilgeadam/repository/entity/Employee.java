package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Employee extends BaseEntity{

    @Id
    private String id;

    private String name;
    private String secondName;
    private String surname;
    private String photo;
    private Long birthDate; //TODO: date mi long mu?
    private String birthplace;
    private String tcNo;
    private Date startDateToWork; //TODO: date mi long mu?
    private Date resignationDate; //işten çıkış tarihi //TODO: date mi long mu?

    @Builder.Default
    private boolean isActive=true; //TODO: boolean mı? enum mu?
    private String profession; //meslek
    private String department;
    private String company;
    private String email; //ad.soyad@bilgeadamboost.com
    private String address;
    private String phone;
    private Long salary; //TODO: long?









}
