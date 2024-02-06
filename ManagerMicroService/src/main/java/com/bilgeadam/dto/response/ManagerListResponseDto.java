package com.bilgeadam.dto.response;

import com.bilgeadam.repository.entity.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ManagerListResponseDto {

    private String photo;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String profession;
    private String department;
    private Long userId;
    private String company;

}
