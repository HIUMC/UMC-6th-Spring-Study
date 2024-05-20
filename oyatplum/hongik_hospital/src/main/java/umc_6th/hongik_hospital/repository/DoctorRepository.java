package umc_6th.hongik_hospital.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc_6th.hongik_hospital.domain.Doctor;
import umc_6th.hongik_hospital.domain.Patient;

import javax.print.Doc;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DoctorRepository {

    private final EntityManager em;

    @Transactional
    public void save(Doctor doctor){
        em.persist(doctor);
    }

    public Doctor findById(Long id){
        return em.find(Doctor.class, id);
    }

}
