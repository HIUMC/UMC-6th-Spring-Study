<<<<<<< HEAD:do-dop/스프링_기본/core/src/main/java/hello/core/AutoAppConfig.java
package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

=======
package umc_6th.spring_principles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
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
>>>>>>> main:oyatplum/spring_principles/src/main/java/umc_6th/spring_principles/AutoAppConfig.java
}
