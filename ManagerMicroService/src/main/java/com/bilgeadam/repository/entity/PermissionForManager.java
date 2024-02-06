package com.bilgeadam.repository.entity;

import com.bilgeadam.utility.enums.EApprovalStatus;
import com.bilgeadam.utility.enums.EPermissionType;
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
public class PermissionForManager extends BaseEntity{
    @Id
    private String id;
    private String permissionId;
    private Long userId;
    private String name;
    private String surname;
    private EPermissionType permissionType;
    private LocalDate startDate;
    private LocalDate endDate;
    @Builder.Default
    private LocalDate dateOfRequest = LocalDate.now();
    private int days;
    @Builder.Default
    private EApprovalStatus approvalStatus=EApprovalStatus.PENDING_APPROVAL;
    private LocalDate replyDate;
    private String description;
    private String company;
}
