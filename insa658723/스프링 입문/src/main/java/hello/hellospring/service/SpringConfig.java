package hello.hellospring.service;

import hello.hellospring.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

  private final MemberRepository memberRepository;

  @Autowired
  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Bean // 스프링 빈을 직접 등록하겠다는 뜻.
  public MemberService memberService() {

    return new MemberService(memberRepository);
  }

  @Bean
  public MemberRepository memberRepository() {
    //return new JdbcMemberRepository(dataSource);
//    return  new JdbcTemplateMemberRepository(dataSource);
    return new JpaMemberRepository(em);
  }
}
