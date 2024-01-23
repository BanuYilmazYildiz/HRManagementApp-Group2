package com.bilgeadam.repository.entity;

import com.bilgeadam.utility.enums.EApprovalStatus;
import lombok.AllArgsConstructor;
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
public class Expense {
    @Id
    private String id;
    private Long userId;//String employeeId
    private String name;
    private String surname;
    private EExpenseType expenseType; // Harcama türü
    private Double expenseAmount; // Harcama tutarı
    private ECurrency currency; // para birimi
    private EApprovalStatus approvalStatus; // onay durumu
    private LocalDate dateOfRequest; //talep tarihi
    private LocalDate dateOfResponse; // cevaplama tarihi
    private MultipartFile expenseField; // eklenecek dosya
}
