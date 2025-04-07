package Packit.spring.web.controller;

import Packit.spring.apiPayload.ApiResponse;
import Packit.spring.apiPayload.code.status.SuccessStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello", produces = "application/json")
    public ApiResponse<String> sayHello() {
        return ApiResponse.onSuccess(SuccessStatus._OK, "Hello, Packit! 👋");
    }

}
