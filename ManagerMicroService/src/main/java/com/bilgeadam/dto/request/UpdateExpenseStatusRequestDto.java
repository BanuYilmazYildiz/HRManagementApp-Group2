package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.EApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateExpenseStatusRequestDto {

    private String expenseId;
    private EApprovalStatus approvalStatus;
}
