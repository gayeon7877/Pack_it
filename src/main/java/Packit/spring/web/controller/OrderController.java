package Packit.spring.web.controller;

import Packit.spring.domain.enums.OrderEvent;
import Packit.spring.domain.enums.OrderState;
import Packit.spring.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/event")
    public String handleEvent(
            @RequestParam OrderState currentState,
            @RequestParam OrderEvent event
    ) {
        return orderService.processOrderEvent(currentState, event);
    }
}
