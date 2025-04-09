package Packit.spring.service;

import Packit.spring.domain.Order;
import Packit.spring.domain.Store;
import Packit.spring.domain.enums.OrderEvent;
import Packit.spring.domain.enums.OrderState;
import Packit.spring.repository.OrderRepository;
import Packit.spring.repository.StoreRepository;
import Packit.spring.web.dto.CreateOrderRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private StateMachineFactory<OrderState, OrderEvent> orderFactory;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StoreRepository storeRepository;

    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }

    public Order createOrder(CreateOrderRequest request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("Store not found: " + request.getStoreId()));

        Order order = new Order();
        order.setStore(store);
        order.setCustomerName(request.getCustomerName());
        order.setStatus(OrderState.REQUESTED); // Initial state
        order.setMenu_id(request.getMenuId());
        order.setQuantity(request.getQuantity());
        order.setFee(request.getFee());

        return orderRepository.save(order);
    }

    @Transactional
    public String handleEvent(Long orderId, OrderEvent event) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        OrderState current = order.getStatus();
        String result = processOrderEvent(current, event);

        if (result.startsWith("✅")) {
            StateMachine<OrderState, OrderEvent> stateMachine = orderFactory.getStateMachine();
            stateMachine.stop();
            stateMachine.getStateMachineAccessor()
                    .doWithAllRegions(accessor -> accessor.resetStateMachine(
                            new DefaultStateMachineContext<>(current, null, null, null, null)));
            stateMachine.start();
            stateMachine.sendEvent(event);
            order.setStatus(stateMachine.getState().getId());
            orderRepository.save(order);
        }

        return result;
    }

    public String processOrderEvent(OrderState currentState, OrderEvent orderEvent) {
        // Create and initialize the state machine
        StateMachine<OrderState, OrderEvent> stateMachine = orderFactory.getStateMachine();
        stateMachine.stop();

        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(accessor -> accessor.resetStateMachine(
                        new DefaultStateMachineContext<>(currentState, null, null, null, null)
                ));

        stateMachine.start();

        boolean accepted = stateMachine.sendEvent(orderEvent);

        if (!accepted) {
            return "Event " + orderEvent + " is not allowed in state " + currentState + ".";
        }

        State<OrderState, OrderEvent> newState = stateMachine.getState();
        return "State changed from " + currentState + " to " + newState.getId() + ".";
    }
}
