package umc_6th.spring_principles.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
