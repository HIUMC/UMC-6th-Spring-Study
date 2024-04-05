package umc_6th.spring_principles;

import umc_6th.spring_principles.discount.DiscountPolicy;
import umc_6th.spring_principles.discount.FixDiscountPolicy;
import umc_6th.spring_principles.member.MemberService;
import umc_6th.spring_principles.member.MemberServiceImpl;
import umc_6th.spring_principles.member.MemoryMemberRepository;
import umc_6th.spring_principles.order.OrderService;
import umc_6th.spring_principles.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
