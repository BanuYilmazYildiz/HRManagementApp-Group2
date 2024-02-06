package com.bilgeadam.rabbitmq.model;

import com.bilgeadam.utility.enums.EAdvanceAmount;
import com.bilgeadam.utility.enums.EApprovalStatus;
import com.bilgeadam.utility.enums.ECurrency;
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

public class CreateAdvanceModel implements Serializable {

    private String advanceId;
    private Long userId;
    private String name;
    private String surname;
    private EAdvanceAmount amountOfRequest;
    @Builder.Default
    private LocalDate dateOfRequest=LocalDate.now();
    private LocalDate replyDate;
    @Builder.Default
    private EApprovalStatus approvalStatus = EApprovalStatus.PENDING_APPROVAL;
    private  String  advanceAmountWithSalary;
    private ECurrency currency;
    private String company;
}
