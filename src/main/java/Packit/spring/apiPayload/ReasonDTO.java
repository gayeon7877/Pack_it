package Packit.spring.apiPayload;

import Packit.spring.apiPayload.code.status.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReasonDTO {
    private String code;
    private String message;

    public ReasonDTO(BaseCode status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }
}
