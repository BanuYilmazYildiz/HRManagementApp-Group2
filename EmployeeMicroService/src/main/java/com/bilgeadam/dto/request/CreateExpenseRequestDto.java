package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.ECurrency;
import com.bilgeadam.utility.enums.EExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateExpenseRequestDto {
    private String token;
    private EExpenseType expenseType;
    private Double expenseAmount;
    private ECurrency currency;
    //private EApprovalStatus approvalStatus;
   // private LocalDate dateOfRequest;
    // private MultipartFile expenseField; // eklenecek dosya. TODO: MONGO DB MULTİPART FİLE TUTMUYOR
}
