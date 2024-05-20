package umc_6th.spring_principles.member;

import org.springframework.stereotype.Component;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD:do-dop/스프링_기본/core/src/main/java/hello/core/member/MemoryMemberRepository.java
@Primary
=======
>>>>>>> main:oyatplum/spring_principles/src/main/java/umc_6th/spring_principles/member/MemoryMemberRepository.java
@Component
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
