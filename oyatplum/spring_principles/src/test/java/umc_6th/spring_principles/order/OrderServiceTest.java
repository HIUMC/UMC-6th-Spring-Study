package umc_6th.spring_principles.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import umc_6th.spring_principles.member.Grade;
import umc_6th.spring_principles.member.Member;
import umc_6th.spring_principles.member.MemberService;
import umc_6th.spring_principles.member.MemberServiceImpl;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
