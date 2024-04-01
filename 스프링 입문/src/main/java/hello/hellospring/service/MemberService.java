package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 이 어노테이션이 스프링이 올라올때 MemberService를 스프링컨테이너에 연결시켜준다.
@Transactional
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
  /**
   * 회원가입
   * @param member
   * @return
   */
  public Long join(Member member) {
    //같은 이름이 있는 중복회원x
    validateDuplicateMemeber(member); // 중복 회원 금지
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMemeber(Member member) {
    memberRepository.findByName(member.getName())
      .ifPresent(m -> {
        throw new IllegalStateException("이미 존재하는 회원입니다.");
    });
  }

  /**
   * 전체 회원 조회
   * @return
   */

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }

}
