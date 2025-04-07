package Packit.spring.domain;

import Packit.spring.domain.common.BaseEntity;
import Packit.spring.domain.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String nickname;

    private String profile;

    @Enumerated(EnumType.STRING)
    private PaymentMethod payment;

    private boolean socialLogin;

    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks;

    @OneToMany
    private List<Order> orders;

}
