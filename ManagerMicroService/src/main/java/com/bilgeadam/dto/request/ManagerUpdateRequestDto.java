package com.bilgeadam.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ManagerUpdateRequestDto {
    private String token;
    @NotEmpty(message = "Adres boş olamaz")
    private String address;
    @NotEmpty(message = "Telefon numarası boş olamaz")
    @Pattern(regexp = "\\d+", message = "Telefon numarası sadece sayısal değerler içermelidir")
    @Size(min = 11, max = 11, message = "Telefon numarası 11 karakter uzunluğunda olmalıdır")
    @Indexed(unique = true)
    private String phone;



}
