package umc_6th.springboot_jpa.repository;

import lombok.Getter;
import lombok.Setter;
import umc_6th.springboot_jpa.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {
    private String memberName; // 회원 이름
    private OrderStatus orderStatus; // 주문 상태 [ORDER, CANCEL]
}
