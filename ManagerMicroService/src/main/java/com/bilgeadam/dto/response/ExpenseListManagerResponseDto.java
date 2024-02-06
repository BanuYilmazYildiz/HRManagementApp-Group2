package com.bilgeadam.dto.response;

import com.bilgeadam.utility.enums.EApprovalStatus;
import com.bilgeadam.utility.enums.ECurrency;
import com.bilgeadam.utility.enums.EExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpenseListManagerResponseDto {
    private String id;
    private Long userId;
    private String expenseId;
    private String name;
    private String surname;
    private EExpenseType expenseType; // Harcama türü
    private Double expenseAmount; // Harcama tutarı
    private ECurrency currency; // para birimi
    @Builder.Default
    private EApprovalStatus approvalStatus=EApprovalStatus.PENDING_APPROVAL; // onay durumu
    @Builder.Default
    private LocalDate dateOfRequest = LocalDate.now(); //talep tarihi
    private LocalDate dateOfResponse;
}
