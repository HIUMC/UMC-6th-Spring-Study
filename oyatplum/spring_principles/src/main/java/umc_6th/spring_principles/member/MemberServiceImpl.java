package umc_6th.spring_principles.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

<<<<<<< HEAD:dldusgh318/core/src/main/java/hello/core/member/MemberServiceImpl.java
    @Autowired //자동의존관계 주입 필요
=======
    @Autowired
>>>>>>> 603252662a71571ea71544fb6fe44e71ee77fe0b:oyatplum/spring_principles/src/main/java/umc_6th/spring_principles/member/MemberServiceImpl.java
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
