package com.bilgeadam.dto.response;

import com.bilgeadam.utility.enums.EApprovalStatus;
import com.bilgeadam.utility.enums.ECurrency;
import com.bilgeadam.utility.enums.EExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponseDto {
    private EExpenseType expenseType;
    private Double expenseAmount;
    private ECurrency currency;
    private LocalDate dateOfResponse;
    private EApprovalStatus approvalStatus;
    private LocalDate dateOfRequest;
}
