package Packit.spring.service;

import Packit.spring.domain.*;
import Packit.spring.web.dto.cart.AddToCartRequest;
import Packit.spring.repository.CartItemRepository;
import Packit.spring.repository.CartRepository;
import Packit.spring.repository.MenuRepository;
import Packit.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final CartItemRepository cartItemRepository;

    public void addItemToCart(AddToCartRequest request) {
        // 유저 조회
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // 메뉴 조회
        Menu menu = menuRepository.findById(request.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));

        // 장바구니 조회 (없으면 생성)
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(Cart.builder().user(user).build()));

        // 기존에 해당 메뉴가 장바구니에 있는지 확인
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getMenu().getId().equals(menu.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            // 수량만 업데이트
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            cartItemRepository.save(item); // 변경된 수량 저장
        } else {
            // 새 아이템 생성
            CartItem newItem = CartItem.builder()
                    .menu(menu)
                    .quantity(request.getQuantity())
                    .cart(cart)
                    .build();
            cartItemRepository.save(newItem);
            cart.getCartItems().add(newItem); // cart에 직접 추가
        }

        // cart 자체도 저장
        cartRepository.save(cart);
    }
}
