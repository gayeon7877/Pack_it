package Packit.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_status_logs")
public class OrderStatusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private OrderStatus toStatus;

    private LocalDateTime changedAt;

    public OrderStatusLog(Order order, OrderStatus toStatus) {
        this.order = order;
        this.toStatus = toStatus;
        this.changedAt = LocalDateTime.now();
    }
}
