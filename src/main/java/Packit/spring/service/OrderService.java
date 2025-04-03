package Packit.spring.service;

import Packit.spring.domain.Order;
import Packit.spring.domain.OrderStatus;
import Packit.spring.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order updateStatus(Long orderId, OrderStatus toStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.updateStatus(toStatus);
        return orderRepository.save(order);
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }
    @Transactional
    public Order createOrder(String customerName) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setStatus(OrderStatus.REQUESTED); // 기본 상태
        return orderRepository.save(order);
    }
}

