package Packit.spring.web.controller;

import Packit.spring.domain.Order;
import Packit.spring.domain.OrderStatus;
import Packit.spring.service.OrderService;
import Packit.spring.web.dto.CreateOrderRequest;
import Packit.spring.web.dto.UpdateStatusRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void updateStatus_상태_변경_성공() throws Exception {
        // Given
        Long orderId = 1L;
        UpdateStatusRequest request = new UpdateStatusRequest();
        request.setToStatus(OrderStatus.PACKED);

        // When & Then
        mockMvc.perform(patch("/api/orders/{id}/status", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("updated well"));
    }

    @Test
    void createOrder_주문_생성_성공() throws Exception {
        // Given
        CreateOrderRequest request = new CreateOrderRequest();
        request.setCustomerName("테스트 고객");

        Order mockOrder = new Order();
        mockOrder.setId(1L);
        mockOrder.setCustomerName("테스트 고객");
        mockOrder.setStatus(OrderStatus.REQUESTED);

        when(orderService.createOrder(any())).thenReturn(mockOrder);

        // When & Then
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("테스트 고객"))
                .andExpect(jsonPath("$.status").value("REQUESTED"));
    }
}
