package umc_6th.spring_principles;

import umc_6th.spring_principles.member.Grade;
import umc_6th.spring_principles.member.Member;
import umc_6th.spring_principles.member.MemberService;
import umc_6th.spring_principles.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
