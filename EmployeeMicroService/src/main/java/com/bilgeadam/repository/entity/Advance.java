package com.bilgeadam.repository.entity;

import com.bilgeadam.utility.enums.EApprovalStatus;
import lombok.AllArgsConstructor;
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
public class Advance extends BaseEntity{

    @Id
    private String id;
    private Long userId;//String employeeId
    //  private String nameEmployee;
//  private String surnameEmployee;
    private Double amountOfRequest;
    private LocalDate dateOfRequest;
    private LocalDate replyDate;
    private EApprovalStatus approvalStatus;

}
