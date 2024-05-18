package umc_6th.hongik_hospital.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc_6th.hongik_hospital.domain.Hospital;

import java.lang.reflect.Member;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalRepository {

    private final EntityManager em;

    @Transactional
    public void save(Hospital hospital){
        em.persist(hospital);
    }

    public Hospital findById(Long id){
        return em.find(Hospital.class, id);
    }

    public List<Hospital> findAll(){
        return em.createQuery("select m from Hospital m", Hospital.class).getResultList();
    }

    public List<Hospital> findByName(String name){
        return em.createQuery("select m from Hospital m where m.hospital_name = :name", Hospital.class)
                .setParameter("name", name)
                .getResultList();
    }

}
