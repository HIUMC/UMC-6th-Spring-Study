package toyproject.hongik_hospital.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.hongik_hospital.domain.Hospital;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HospitalRepository {
    private final EntityManager em;

    public void save(Hospital hospital){em.persist(hospital);}
    public Hospital findOne(Long id){return em.find(Hospital.class,id);}


}
