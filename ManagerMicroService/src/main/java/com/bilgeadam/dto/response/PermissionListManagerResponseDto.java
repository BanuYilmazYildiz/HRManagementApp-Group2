package com.bilgeadam.dto.response;

import com.bilgeadam.utility.enums.EApprovalStatus;
import com.bilgeadam.utility.enums.EPermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionListManagerResponseDto {
    private String id;
    private Long userId;
    private String permissionId;
    private String name;
    private String surname;
    private EPermissionType permissionType;
    private LocalDate startDate;
    private LocalDate endDate;
    private EApprovalStatus approvalStatus;
    private String description;
    private LocalDate dateOfRequest;
    private LocalDate replyDate;
}
