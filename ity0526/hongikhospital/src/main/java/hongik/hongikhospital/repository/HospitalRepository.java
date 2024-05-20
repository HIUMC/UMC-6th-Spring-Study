package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Doctor;
import hongik.hongikhospital.domain.Hospital;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class HospitalRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Hospital hospital) {
        em.persist(hospital);
    }

    public Hospital findOne(Long id) {
        return em.find(Hospital.class, id);
    }
}
