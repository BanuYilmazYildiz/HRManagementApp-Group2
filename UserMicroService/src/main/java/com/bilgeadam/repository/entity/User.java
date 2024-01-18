package com.bilgeadam.repository.entity;

import com.bilgeadam.utility.enums.ERole;
import lombok.AllArgsConstructor;
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
public class User extends BaseEntity{
    @Id
    private String id;

    private String name;
    private String secondName;
    private String surname;
    private String email; //ad.soyad@bilgeadamboost.com
    private String password;
    private ERole role;
    private String tcNo;
    private String company;
    private Date startDateToWork;
    private Date resignationDate; //işten çıkış tarihi






}
