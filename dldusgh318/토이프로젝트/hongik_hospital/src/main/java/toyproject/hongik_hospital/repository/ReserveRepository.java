package toyproject.hongik_hospital.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.hongik_hospital.domain.Reserve;

@Repository
@RequiredArgsConstructor
public class ReserveRepository {
    private final EntityManager em;

    public void save(Reserve reserve){em.persist(reserve);}
    public Reserve findOne(Long id){return em.find(Reserve.class,id);}
}
