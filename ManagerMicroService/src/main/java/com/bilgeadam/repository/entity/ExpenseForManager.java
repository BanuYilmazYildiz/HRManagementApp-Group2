
package com.bilgeadam.repository.entity;

import com.bilgeadam.utility.enums.EApprovalStatus;
import com.bilgeadam.utility.enums.ECurrency;
import com.bilgeadam.utility.enums.EExpenseType;
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
public class ExpenseForManager extends BaseEntity {
    @Id
    private String id;
    private Long userId;//String employeeId
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
    private String company;
}

