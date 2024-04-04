package umc_6th.spring_principles;

import umc_6th.spring_principles.member.Grade;
import umc_6th.spring_principles.member.Member;
import umc_6th.spring_principles.member.MemberService;
import umc_6th.spring_principles.member.MemberServiceImpl;
import umc_6th.spring_principles.order.Order;
import umc_6th.spring_principles.order.OrderService;
import umc_6th.spring_principles.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order.toString());
    }
}