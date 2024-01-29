package com.bilgeadam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FindAllEmployeeResponseDto {

    private String photo;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String profession;
    private String department;
    private Long userId;
}
