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
    USER_NOT_FOUND(4112,"Kulanici bulunamadi..." , HttpStatus.BAD_REQUEST ),
    PASSWORD_MISSMATCH(4113,"Şifreler uyusmuyor" , HttpStatus.BAD_REQUEST ),
    INVALID_TOKEN(4114,"Gecersiz token" ,HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4115,"Token olusturulamadi..." ,HttpStatus.BAD_REQUEST ),
//    ACCOUNT_NOT_ACTIVE(4116,"Hesabınız aktif edilmemiştir. Lütfen hesabınızı aktif hale getiriniz..." , HttpStatus.FORBIDDEN),
//    USER_NOT_CREATED(4117,"Kullanici profili olusturulamadi...",HttpStatus.BAD_REQUEST),
//    ROLE_NOT_FOUND(4118,"Boyle bir kullanici rolu bulunmamaktadir..." ,HttpStatus.BAD_REQUEST );

    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
