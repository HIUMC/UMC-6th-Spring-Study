package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
     /*   AppConfig appConfig=new AppConfig();
        MemberService memberService= appConfig.memberService(); //memberServiceImpl이 들어가있음
        */

        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);   //long타입이라 L 붙여줘야함
        memberService.join(member);

        Member findMember=memberService.findMember(1L);
        System.out.println("newMember= "+member.getName());
        System.out.println("findMember= "+findMember.getName());
    }
}
