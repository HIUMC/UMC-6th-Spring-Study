package repository;

import domain.Department;
import domain.Doctor;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {

  private final EntityManager em;

  public void save(Department department) {
    em.persist(department);
  }

  public Department findById(Long id) {
    return em.find(Department.class, id);
  }
}
