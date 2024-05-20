package umc_6th.hongik_hospital.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc_6th.hongik_hospital.domain.Reserve;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReserveRepository {

    private final EntityManager em;

    @Transactional
    public Long save(Reserve reserve){
        em.persist(reserve);
        return reserve.getReserve_id();
    }

    public Reserve findById(Long id){
        return em.find(Reserve.class, id);
    }

}
