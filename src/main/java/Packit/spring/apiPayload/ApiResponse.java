package Packit.spring.apiPayload;

import Packit.spring.apiPayload.code.status.BaseCode;
import Packit.spring.apiPayload.code.status.BaseErrorCode;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ReasonDTO code;

    public ApiResponse(boolean success, T data, ReasonDTO code) {
        this.success = success;
        this.data = data;
        this.code = code;
    }

    public static <T> ApiResponse<T> onSuccess(BaseCode status, T data) {
        return new ApiResponse<>(true, data, new ReasonDTO(status));
    }


    public static <T> ApiResponse<T> OnFailure(BaseErrorCode status) {
        return new ApiResponse<>(false, null, new ReasonDTO(status));
    }
}