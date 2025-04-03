package Packit.spring.domain;

public enum OrderStatus {
    REQUESTED,
    PACKED,
    PICKUP_READY,
    PICKED_UP,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus next) {
        return switch (this) {
            case REQUESTED -> next == PACKED || next == CANCELLED;
            case PACKED -> next == PICKUP_READY || next == CANCELLED;
            case PICKUP_READY -> next == PICKED_UP;
            default -> false;
        };
    }
}
