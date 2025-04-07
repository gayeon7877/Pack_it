package Packit.spring.apiPayload;

import lombok.Getter;

@Getter
public class ErrorReasonDTO {
    private String code;
    private String message;

    public ErrorReasonDTO() {}

    public ErrorReasonDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}