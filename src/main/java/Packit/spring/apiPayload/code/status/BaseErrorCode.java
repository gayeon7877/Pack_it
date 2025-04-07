package Packit.spring.apiPayload.code.status;

import Packit.spring.apiPayload.ErrorReasonDTO;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode extends BaseCode {
    HttpStatus getHttpStatus();
    ErrorReasonDTO getReason();
    ErrorReasonDTO getReason(String detailMessage);
}
