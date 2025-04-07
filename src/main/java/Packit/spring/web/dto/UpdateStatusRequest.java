package Packit.spring.web.dto;

import Packit.spring.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusRequest {
    private OrderStatus toStatus;
}
