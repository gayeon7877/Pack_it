package Packit.spring.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
    private String customerName;
    private Long storeId;
    private int menuId;
    private int quantity;
    private int fee;
}

