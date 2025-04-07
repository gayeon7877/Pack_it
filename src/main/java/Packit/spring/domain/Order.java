package Packit.spring.domain;

import Packit.spring.domain.common.BaseEntity;
import Packit.spring.domain.enums.OrderStatus;
import Packit.spring.domain.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;


    private String customerName;



    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderStatusLog> statusLogs = new ArrayList<>();

    public void updateStatus(OrderStatus nextStatus) {
        if (!this.status.canTransitionTo(nextStatus)) {
            throw new IllegalStateException("Invalid status transition: " + this.status + " → " + nextStatus);
        }
        this.status = nextStatus;
        this.statusLogs.add(new OrderStatusLog(this, nextStatus));
    }

    @Enumerated(EnumType.STRING)
    private PaymentMethod payment;

    private String requirement;
    private int menu_id;
    private int quantity;
    private int fee;
}
