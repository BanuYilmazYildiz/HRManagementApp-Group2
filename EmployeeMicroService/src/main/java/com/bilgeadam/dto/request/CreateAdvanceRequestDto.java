package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.EAdvanceAmount;
import com.bilgeadam.utility.enums.ECurrency;
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

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate replyDate;
    private ECurrency currency;
}
