package Packit.spring.domain;

import Packit.spring.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;


    private String storeName;
    private String storeTel;
    private String storeAddress;
    private String storeBossName;
    private String storeHours;
    private String storeIntroduction;
    private String storeNotice;
    private String storeImage;
    private String storeLicense;


}
