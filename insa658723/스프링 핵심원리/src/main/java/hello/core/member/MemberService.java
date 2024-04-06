package hello.core.member;

// 회원서비스 인터페이스(역할)
public interface MemberService {

  // 회원가입
  void join(Member member);

  // 회원조회
  Member findMember(Long memberId);
}
