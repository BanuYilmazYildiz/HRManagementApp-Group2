package com.bilgeadam.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FindAllEmployeeRequestDto {
    private String token;
    @NotEmpty(message = "Şirket ismi boş olamaz")
    private String company;
}
