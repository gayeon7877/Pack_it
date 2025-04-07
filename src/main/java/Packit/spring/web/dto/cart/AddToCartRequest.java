package Packit.spring.web.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequest {
    private Long userId;
    private Long menuId;
    private int quantity;
}