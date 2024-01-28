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
public class PermissionResponseDto {

    private EPermissionType permissionType;
    private LocalDate startDate;
    private LocalDate endDate;
    private EApprovalStatus approvalStatus;



}
