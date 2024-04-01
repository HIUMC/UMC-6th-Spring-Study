package umc_6th.spring_introduction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import umc_6th.spring_introduction.repository.MemberRepository;
import umc_6th.spring_introduction.repository.MemoryMemberRepository;
import umc_6th.spring_introduction.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
