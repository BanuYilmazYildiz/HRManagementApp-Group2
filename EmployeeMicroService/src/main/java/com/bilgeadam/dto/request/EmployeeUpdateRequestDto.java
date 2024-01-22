package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeUpdateRequestDto {
    private String token;


    private String address;
    private String phone;



}
