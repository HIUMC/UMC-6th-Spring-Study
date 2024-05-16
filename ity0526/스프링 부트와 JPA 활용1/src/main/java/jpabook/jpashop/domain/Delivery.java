package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dlivery {

    @Id
    @GeneratedValue
    @Column(name = "dlivery_id")
    private Long id;

    @OneToOne
    private Order order;

    @Embedded
    private Address address;


    @Enumerated(EnumType.STRING)
    private DliveryStatus status;
}
