package com.bilgeadam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeFindByUserIdResponseDto {

    private String name;
    private String secondName;
    private String surname;
    private String photo;
    private String email;

}
