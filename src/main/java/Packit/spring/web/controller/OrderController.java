package Packit.spring.web.controller;

import Packit.spring.domain.Order;
import Packit.spring.service.OrderService;
import Packit.spring.web.dto.CreateOrderRequest;
import Packit.spring.web.dto.UpdateStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusRequest request) {
        orderService.updateStatus(id, request.getToStatus());
        return ResponseEntity.ok("updated well");
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(request.getCustomerName());
        return ResponseEntity.ok(order);
    }

}