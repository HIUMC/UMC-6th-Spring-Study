package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//애플리케이션 전체를 설정하고 구성
@Configuration

public class AppConfig {

  //여기서 구현체를 생성해서 넣어준다.
  //생성자 주입

  //@Bean memberService -> new MemoryMemberRepository()
  //@Bean orderService -> new MemoryMemberRepository()

  //call AppConfig.memberService
  //call AppConfig.memberRepository
  //call AppConfig.memberRepository
  //call AppConfig.orderService
  //call AppConfig.memberRepository
  @Bean
  public MemberService memberService() {
    //객체 인스턴스의 참조를 생성자를 통하여 주입해준다.
    //객체를 생성하고 연결하는 역할.
    System.out.println("call AppConfig.memberService");
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("call AppConfig.memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public OrderService orderService() {
    System.out.println("call AppConfig.orderService");
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    //return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }
}
