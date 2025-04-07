package Packit.spring.domain;

import Packit.spring.domain.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ValueGenerationType;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;


    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;

    private String menu_name;
    private String about_menu;
    private int price;
    private String container;
    private Boolean insulation;
    private Boolean liquid_seal;
    private String image;



}
