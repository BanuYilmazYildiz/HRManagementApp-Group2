package com.bilgeadam.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateCompanyRequestDto {
    private String token;
    private String companyName;
    private String mersisNumber;//şirketlerin ticaret sicil kayıtlarının dijital ortamda tutulması
    @Pattern(regexp = "\\d+", message = "Telefon numarası sadece sayısal değerler içermelidir")
    @Size(min = 11, max = 11, message = "Telefon numarası 11 karakter uzunluğunda olmalıdır")
    private String phone;
    @Size(min=5,max=250,message = "Text size exceeded")
    private String address;
    @Size(min=5,max=250,message = "Text size exceeded")
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate foundationYear;//firma kuruluş tarihi
    private LocalDate contractStartDate;
    private LocalDate contractFinishDate;
}
