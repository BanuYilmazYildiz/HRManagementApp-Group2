package com.bilgeadam.repository.entity;

import com.bilgeadam.utility.enums.EApproval;
import com.bilgeadam.utility.enums.EPermissionType;
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
public class Permission extends BaseEntity{

    @Id
    private String id;
    private Long userId;//String employeeId
//  private String nameEmployee;
//  private String surnameEmployee;
    private EPermissionType permissionType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dateOfRequest;
    private int days;
    private EApproval approvalStatus;
    private LocalDate replyDate;

}
