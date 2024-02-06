package com.bilgeadam.rabbitmq.model;

import com.bilgeadam.utility.enums.EApprovalStatus;
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
public class UpdateAdvanceStatusModel implements Serializable {
    private String advanceId;
    private EApprovalStatus approvalStatus;
    private LocalDate replyDate;
}
