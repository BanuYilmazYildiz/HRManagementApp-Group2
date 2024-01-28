package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.EAdvanceAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateAdvanceRequestDto {

    String token;
    private EAdvanceAmount amountOfRequest;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dateOfRequest;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate replyDate;
}
