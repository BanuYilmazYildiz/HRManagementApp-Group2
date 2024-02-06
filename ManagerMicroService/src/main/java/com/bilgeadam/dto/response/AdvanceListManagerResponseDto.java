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
public class AdvanceListManagerResponseDto {
    private String id;
    private Long userId;
    private String advanceId;
    private String name;
    private String surname;
    private EAdvanceAmount amountOfRequest;
    private LocalDate replyDate;
    private EApprovalStatus approvalStatus;
    private LocalDate dateOfRequest;
    private  String advanceAmountWithSalary;
    private ECurrency currency;
}
