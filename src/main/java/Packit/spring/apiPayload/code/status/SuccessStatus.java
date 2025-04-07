package Packit.spring.apiPayload.code.status;

import org.springframework.http.HttpStatus;

public enum SuccessStatus implements BaseCode {
    _OK("S200", HttpStatus.OK, "요청 성공"),
    CREATED("S201", HttpStatus.CREATED, "리소스 생성됨");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

    SuccessStatus(String code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getCode() { return code; }

    @Override
    public String getMessage() { return message; }

    @Override
    public HttpStatus getHttpStatus() { return httpStatus; }
}
