package repository;

import domain.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorRepository {
  private final EntityManager em;

  public void save(Doctor doctor) {
    em.persist(doctor);
  }

  public Doctor findById(Long id) {
    return em.find(Doctor.class, id);
  }

  public List<Doctor> findByName(String name) {
    return em.createQuery("select d from Doctor d where d.doctorName=:name")
        .setParameter("name", name)
        .getResultList();
  }

  public List<Doctor> findAll() {
    return em.createQuery("select d from Doctor d", Doctor.class)
        .getResultList();
  }
}
