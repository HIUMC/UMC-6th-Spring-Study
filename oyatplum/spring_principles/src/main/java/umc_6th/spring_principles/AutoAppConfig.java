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
}
