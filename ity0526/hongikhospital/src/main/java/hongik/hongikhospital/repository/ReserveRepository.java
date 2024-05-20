package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Reserve;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReserveRepository {
    private final EntityManager em;

    public void save(Reserve reserve) {
        em.persist(reserve);
    }

    public Reserve findById(Long id) {
        return em.find(Reserve.class, id);
    }



}
