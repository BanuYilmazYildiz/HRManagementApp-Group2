package com.bilgeadam.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCompanyResponseDto {
    private Long id;
    private String companyName;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Title name must be letters only.")
    private String title;
    private String mersisNumber;
    private String phone;
    @Size(min=5,max=250,message = "Text size exceeded")
    private String address;
    @Email
    private String email;
    private LocalDate foundationYear;
    private LocalDate contractStartDate;
    private LocalDate contractFinishDate;
}
