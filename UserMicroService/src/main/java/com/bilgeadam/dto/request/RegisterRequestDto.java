package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDto {

    @NotEmpty(message = "Ad alanını boş bırakmayınız.")
    private String name;
    private String secondName;
    @NotEmpty(message = "Soyadı alanını boş bırakmayınız.")
    private String surname;
    private String photo;
    @NotNull(message = "Dogum tarihi boş olamaz")
    private LocalDate birthday;
    @NotEmpty(message = "Dogum yeri boş olamaz")
    private String birthplace;
    @NotEmpty(message = "TC Kimlik Numarası boş olamaz")
    @Pattern(regexp = "\\d+", message = "TC Kimlik Numarası sadece sayısal değerler içermelidir")
    @Size(min = 11, max = 11, message = "TC Kimlik Numarası 11 karakter uzunluğunda olmalıdır")
    @Column(unique = true, length = 11)
    private String tcNo;
    @NotNull(message = "İşe başlama tarihi boş olamaz")
    private LocalDate startDateToWork;
    private LocalDate resignationDate; //işten çıkış tarihi
    @NotEmpty(message = "Meslek ismi boş olamaz")
    private String profession; //meslek
    @NotEmpty(message = "Departman ismi boş olamaz")
    private String department;
    @NotEmpty(message = "Şirket ismi boş olamaz")
    private String company;
    @Email
    @NotEmpty(message = "E-mail kısmı boş olamaz")
    private String personalEmail;
    @NotEmpty(message = "Adres boş olamaz")
    private String address;
    @NotEmpty(message = "Telefon numarası boş olamaz")
    @Pattern(regexp = "\\d+", message = "Telefon numarası sadece sayısal değerler içermelidir")
    @Size(min = 11, max = 11, message = "Telefon numarası 11 karakter uzunluğunda olmalıdır")
    @Column(unique = true, length = 11)
    private String phone;
    @NotNull(message = "Maaş boş olamaz")
    private Double salary;
    @Enumerated(EnumType.STRING)
    private ERole role;


}
