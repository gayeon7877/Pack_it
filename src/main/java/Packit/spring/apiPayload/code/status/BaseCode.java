package Packit.spring.apiPayload.code.status;

import org.springframework.http.HttpStatus;

public interface BaseCode {
    String getCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
