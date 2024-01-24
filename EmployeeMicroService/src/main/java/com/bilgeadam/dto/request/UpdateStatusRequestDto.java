package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.EApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateStatusRequestDto {

    private String id;
    private EApprovalStatus approvalStatus;
}
