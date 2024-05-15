package umc_6th.spring_principles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import umc_6th.spring_principles.discount.DiscountPolicy;
import umc_6th.spring_principles.discount.FixDiscountPolicy;
import umc_6th.spring_principles.discount.RateDiscountPolicy;
import umc_6th.spring_principles.member.MemberService;
import umc_6th.spring_principles.member.MemberServiceImpl;
import umc_6th.spring_principles.member.MemoryMemberRepository;
import umc_6th.spring_principles.order.OrderService;
import umc_6th.spring_principles.order.OrderServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService(){
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
