package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Patient;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public class PatientRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Patient patient) {
        em.persist(patient);
    }

    public Patient findOne(Long id) {
        return em.find(Patient.class, id);
    }

    public List<Patient> findByName(String name) {
        return em.createQuery("select p from Patient p where p.patientname=:name")
                .setParameter("name", name)
                .getResultList();
    }
}
