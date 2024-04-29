package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@ComponentScan (
        basePackages = "hello.core", //어느 패키지부터 찾을건지 위치 지정 가능
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Configuration.class)
        //예외를 정의해줌
)
@Configuration
public class AutoAppConfig {


/*    @Bean(name="menortMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }*/

}
