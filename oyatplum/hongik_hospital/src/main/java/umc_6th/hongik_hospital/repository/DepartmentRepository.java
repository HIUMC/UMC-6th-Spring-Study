package umc_6th.hongik_hospital.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc_6th.hongik_hospital.domain.Department;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {

    private final EntityManager em;

    public void save(Department department){
        em.persist(department);
    }

    public Department findById(Long id){
        return em.find(Department.class, id);
    }
}
