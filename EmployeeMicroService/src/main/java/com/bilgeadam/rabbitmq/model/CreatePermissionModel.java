package com.bilgeadam.rabbitmq.model;

import com.bilgeadam.utility.enums.EApprovalStatus;
import com.bilgeadam.utility.enums.EPermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermissionModel  implements Serializable {

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
