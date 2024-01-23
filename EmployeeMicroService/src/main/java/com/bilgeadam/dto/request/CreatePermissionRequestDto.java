package com.bilgeadam.dto.request;


import com.bilgeadam.utility.enums.EPermissionType;
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
public class CreatePermissionRequestDto {
    String token;
    EPermissionType ePermissionType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //ISO 8601 formatı, "yyyy-MM-dd" şeklinde tarihleri temsil etmek için geniş bir şekilde kabul edilen bir standarttır.
    LocalDate startDate;

    //Frontend'de, tarihleri düzenlemek ve göstermek için genellikle tarih seçici
    // (date picker) veya takvim komponentleri kullanılır.
    //<input type="date" id="startDate" name="startDate">
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endDate;
    LocalDate dateOfRequest;
}
