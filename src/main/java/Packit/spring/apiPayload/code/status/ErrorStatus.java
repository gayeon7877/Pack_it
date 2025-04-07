package Packit.spring.apiPayload.code.status;

import Packit.spring.apiPayload.ErrorReasonDTO;
import org.springframework.http.HttpStatus;

public enum ErrorStatus implements BaseErrorCode {
    INVALID_INPUT("E400", HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),
    NOT_FOUND("E404", HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;

    ErrorStatus(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    @Override
    public String getCode() { return code; }

    @Override
    public HttpStatus getHttpStatus() { return status; }

    @Override
    public String getMessage() { return message; }

    @Override
    public ErrorReasonDTO getReason() {
        return new ErrorReasonDTO(code, message);
    }

    @Override
    public ErrorReasonDTO getReason(String detailMessage) {
        return new ErrorReasonDTO(code, detailMessage);
    }
}
