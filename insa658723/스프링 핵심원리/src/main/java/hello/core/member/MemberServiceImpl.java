package hello.core.member;

public class MemberServiceImpl implements MemberService{

  // MemoryMemberRepisitory 구현객체 설정 삭제
  private final MemberRepository memberRepository;

  //MemberRepository에 무엇이 들어갈지를 생성자를 통해서 정한다.
  //MemberServiceImpl에 MemoryMemberRepository에 관한 코드가 없다. 즉, 의존하지 않는다.
  public MemberServiceImpl(MemberRepository memberRepository) {

    this.memberRepository = memberRepository;
  }

  // 회원가입.
  @Override
  public void join(Member member) {
    memberRepository.save((member));
  }

  // 회원조회.
  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
