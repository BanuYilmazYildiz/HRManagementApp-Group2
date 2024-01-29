package com.bilgeadam.dto.response;

import com.bilgeadam.utility.enums.EAdvanceAmount;
import com.bilgeadam.utility.enums.EApprovalStatus;
import com.bilgeadam.utility.enums.ECurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class AdvanceResponseDto {
    private EAdvanceAmount amountOfRequest;
    private LocalDate replyDate;
    private EApprovalStatus approvalStatus;
    private LocalDate dateOfRequest;
    private  String advanceAmountWithSalary;
    private ECurrency currency;


}
