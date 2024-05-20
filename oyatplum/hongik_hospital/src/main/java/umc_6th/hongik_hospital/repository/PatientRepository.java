package umc_6th.hongik_hospital.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc_6th.hongik_hospital.domain.Hospital;
import umc_6th.hongik_hospital.domain.Patient;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientRepository {
    private final EntityManager em;

    @Transactional
    public void save(Patient patient){
        em.persist(patient);
    }

    public Patient findById(Long id){
        return em.find(Patient.class, id);
    }

    public List<Patient> findByName(String name){
        return em.createQuery("select m from Patient m where m.patient_name = :name", Patient.class)
                .setParameter("name", name)
                .getResultList();
    }

}
