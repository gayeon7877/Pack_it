package Packit.spring.web.controller;

import Packit.spring.apiPayload.ApiResponse;
import Packit.spring.apiPayload.code.status.SuccessStatus;
import Packit.spring.web.dto.cart.AddToCartRequest;
import Packit.spring.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ApiResponse<String> addToCart(@RequestBody AddToCartRequest request) {
        cartService.addItemToCart(request);
        return ApiResponse.onSuccess(SuccessStatus._OK, "장바구니에 추가되었습니다.");
    }
}