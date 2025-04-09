package Packit.spring.web.controller;

import Packit.spring.domain.enums.OrderEvent;
import Packit.spring.domain.enums.OrderState;
import Packit.spring.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    // 1. 유효한 상태 전이 테스트
    @ParameterizedTest
    @CsvSource({
            "REQUESTED, PACK, PACKED",
            "PACKED, MAKE_READY, PICKUP_READY",
            "PICKUP_READY, PICKUP, PICKED_UP",
            "REQUESTED, CANCEL, CANCELLED",
            "PACKED, CANCEL, CANCELLED"
    })
    void validTransitions(OrderState currentState, OrderEvent event, OrderState targetState) throws Exception {
        String expected = String.format("State changed from %s \" to \" %s.", currentState, targetState);

        when(orderService.processOrderEvent(eq(currentState), eq(event)))
                .thenReturn(expected);

        mockMvc.perform(post("/api/orders/event")
                        .param("currentState", currentState.name())
                        .param("event", event.name()))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

//    @ParameterizedTest
//    @CsvSource({
//            "REQUESTED, MAKE_READY",
//            "REQUESTED, PICKUP",
//            "PACKED, PACK",
//            "PACKED, PICKUP",
//            "PICKUP_READY, PACK",
//            "PICKUP_READY, MAKE_READY",
//            "PICKED_UP, PACK",
//            "PICKED_UP, MAKE_READY",
//            "PICKED_UP, PICKUP",
//            "CANCELLED, PACK",
//            "CANCELLED, CANCEL"
//    })
//    void invalidTransitions(OrderState currentState, OrderEvent event) throws Exception {
//        String errorMessage = String.format("Event %s is not allowed in state %s.", event, currentState);
//
//        // Mock OrderService to throw IllegalStateException for invalid transitions
//        when(orderService.processOrderEvent(eq(currentState), eq(event)))
//                .thenThrow(new IllegalStateException(errorMessage));
//
//        // Perform the request and assert the expected behavior
//        mockMvc.perform(post("/api/orders/event")
//                        .param("currentState", currentState.name())
//                        .param("event", event.name()))
//                .andExpect(status().isConflict()) // Expect HTTP 409 Conflict
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalStateException))
//                .andExpect(content().string(errorMessage)); // Assert the error message matches
//    }

    // 3. 취소 관련 엣지 케이스 테스트
    @Test
    void cancelFromMultipleStates() throws Exception {
        // REQUESTED → CANCELLED
        mockMvc.perform(post("/api/orders/event")
                        .param("currentState", OrderState.REQUESTED.name())
                        .param("event", OrderEvent.CANCEL.name()))
                .andExpect(status().isOk());

        // PACKED → CANCELLED
        mockMvc.perform(post("/api/orders/event")
                        .param("currentState", OrderState.PACKED.name())
                        .param("event", OrderEvent.CANCEL.name()))
                .andExpect(status().isOk());
    }

    // 4. 파라미터 유효성 검사 테스트
    @Test
    void invalidParameters() throws Exception {
        // 잘못된 상태값
        mockMvc.perform(post("/api/orders/event")
                        .param("currentState", "INVALID_STATE")
                        .param("event", OrderEvent.PACK.name()))
                .andExpect(status().isBadRequest());

        // 잘못된 이벤트값
        mockMvc.perform(post("/api/orders/event")
                        .param("currentState", OrderState.REQUESTED.name())
                        .param("event", "INVALID_EVENT"))
                .andExpect(status().isBadRequest());
    }

    // 5. 누락된 파라미터 테스트
    @Test
    void missingParameters() throws Exception {
        mockMvc.perform(post("/api/orders/event"))
                .andExpect(status().isBadRequest());
    }
}
