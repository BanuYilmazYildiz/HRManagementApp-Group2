package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_ERROR(5100,"Sunucu Hatasi...", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100,"Parametre Hatasi...", HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4110,"Mail adi veya sifre hatali...", HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4111,"Kullanici adi kullanilmaktadir" ,HttpStatus.BAD_REQUEST),
   MANAGER_NOT_FOUND(4112,"Kulanici bulunamadi..." , HttpStatus.BAD_REQUEST ),
    //    ACTIVATION_CODE_ERROR(4113,"Aktivasyon kodu hatalidir..." , HttpStatus.BAD_REQUEST ),
    INVALID_TOKEN(4114,"Gecersiz token" ,HttpStatus.BAD_REQUEST),

    //    ACCOUNT_NOT_ACTIVE(4116,"Hesabınız aktif edilmemiştir. Lütfen hesabınızı aktif hale getiriniz..." , HttpStatus.FORBIDDEN),
    EMPLOYEE_NOT_CREATED(4117,"Employee profili olusturulamadi...",HttpStatus.BAD_REQUEST),
    //    ROLE_NOT_FOUND(4118,"Boyle bir kullanici rolu bulunmamaktadir..." ,HttpStatus.BAD_REQUEST );
    MANAGER_NOT_CREATED(4118,"Manager olusturulamadi",HttpStatus.BAD_REQUEST),
    EXPENSE_NOT_CREATED(4119,"Expense olusturulamadi",HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_CREATED(4120,"Permission olusturulamadi",HttpStatus.BAD_REQUEST),
    ADVANCE_NOT_CREATED(4121,"Advance olusturulamadi",HttpStatus.BAD_REQUEST),
    COMPANY_NOT_CREATED(4001,"Company olusturulamadi",HttpStatus.BAD_REQUEST),
    COMPANY_ALREADY_EXIST(4002,"Company adi bulunmakta",HttpStatus.BAD_REQUEST),
    COMPANY_NOT_FOUND(4006,"Company bulunamadi.",HttpStatus.BAD_REQUEST);


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
