package com.bilgeadam.rabbitmq.model;

import com.bilgeadam.utility.enums.EApprovalStatus;
import com.bilgeadam.utility.enums.ECurrency;
import com.bilgeadam.utility.enums.EExpenseType;
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
public class CreateExpenseModel implements Serializable {

    private String expenseId;
    private Long userId;
    private String name;
    private String surname;
    private EExpenseType expenseType;
    private Double expenseAmount;
    private ECurrency currency;
    @Builder.Default
    private EApprovalStatus approvalStatus=EApprovalStatus.PENDING_APPROVAL; // onay durumu
    @Builder.Default
    private LocalDate dateOfRequest = LocalDate.now();
    private LocalDate dateOfResponse;
    private String company;
}
