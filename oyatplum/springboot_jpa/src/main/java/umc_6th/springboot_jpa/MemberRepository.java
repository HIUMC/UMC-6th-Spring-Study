package umc_6th.springboot_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import umc_6th.springboot_jpa.domain.Member;

@Repository
public class MemberRepository {


    @PersistenceContext

    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
