package Packit.spring.web.controller;

import Packit.spring.apiPayload.ApiResponse;
import Packit.spring.apiPayload.code.status.SuccessStatus;
import Packit.spring.service.CartService;
import Packit.spring.web.dto.cart.AddToCartRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // mock 초기화
    }

    @Test
    void addToCart_shouldReturnSuccessMessage() {
        // given
        AddToCartRequest request = new AddToCartRequest();
        request.setUserId(1L);
        request.setMenuId(100L);
        request.setQuantity(2);

        // when
        ApiResponse<String> response = cartController.addToCart(request);

        // then
        verify(cartService).addItemToCart(request); // 서비스 메서드가 호출되었는지 확인
        assertEquals("장바구니에 추가되었습니다.", response.getData()); // 메시지 확인
    }
}
