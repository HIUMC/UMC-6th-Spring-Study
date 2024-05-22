package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired //ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 구현체 안넣으면 NullpointEx
    @Override
    public void join(Member member) {
        memberRepository.save(member);
        // 다형성에 의해 MemoryMemberRepository 호출
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public  MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
