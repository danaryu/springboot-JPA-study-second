package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // Ordinal: 0, 1, 2..
    private DeliveryStatus status; // READY, COMP // 중간에 다른 값이 들어가도 안전한 String으로..


}
