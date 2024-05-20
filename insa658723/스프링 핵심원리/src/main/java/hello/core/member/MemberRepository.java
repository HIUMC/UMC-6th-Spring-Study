package hello.core.member;

// 인터페이스(역할)
public interface MemberRepository {

  // 회원을 저장.
  void save(Member member);

  // 회원의 ID로 회원을 찾는 기능.
  Member findById(Long memberId);
}
