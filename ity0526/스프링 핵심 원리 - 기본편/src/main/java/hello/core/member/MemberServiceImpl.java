package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

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


}
