package toyproject.hongik_hospital.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.hongik_hospital.domain.AllReserve;

@Repository
@RequiredArgsConstructor
public class AllRepository {
    private final EntityManager em;

    public void save(AllReserve allReserve){em.persist(allReserve);}
    public AllReserve findOne(Long id){return em.find(AllReserve.class,id);}



}
