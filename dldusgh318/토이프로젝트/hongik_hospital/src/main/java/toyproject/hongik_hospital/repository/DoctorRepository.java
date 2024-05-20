package toyproject.hongik_hospital.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.hongik_hospital.domain.Doctor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorRepository {
    private final EntityManager em;

    public void save(Doctor doctor){em.persist(doctor);}
    public Doctor findOne(Long id){return em.find(Doctor.class,id);}

    public List<Doctor> findAll(){
        return em.createQuery("select d from Doctor d", Doctor.class)
                .getResultList();
    }

    public List<Doctor> findByName(String name){
        return em.createQuery("select d from Doctor d where d.name", Doctor.class)
                .setParameter("name",name)
                .getResultList();
    }
}
