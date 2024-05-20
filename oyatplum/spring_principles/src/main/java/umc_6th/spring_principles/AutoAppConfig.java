<<<<<<< HEAD:dldusgh318/core/src/main/java/hello/core/AutoAppConfig.java
package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
=======
package umc_6th.spring_principles;

>>>>>>> 603252662a71571ea71544fb6fe44e71ee77fe0b:oyatplum/spring_principles/src/main/java/umc_6th/spring_principles/AutoAppConfig.java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
<<<<<<< HEAD:dldusgh318/core/src/main/java/hello/core/AutoAppConfig.java

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

=======
import umc_6th.spring_principles.member.MemberRepository;
import umc_6th.spring_principles.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
        basePackages = "umc_6th.spring_principles.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class
        )
)
public class AutoAppConfig {
        @Bean(name = "memoryMemberRepository")
        MemberRepository memberRepository() {
                return new MemoryMemberRepository();
        }
>>>>>>> 603252662a71571ea71544fb6fe44e71ee77fe0b:oyatplum/spring_principles/src/main/java/umc_6th/spring_principles/AutoAppConfig.java
}
