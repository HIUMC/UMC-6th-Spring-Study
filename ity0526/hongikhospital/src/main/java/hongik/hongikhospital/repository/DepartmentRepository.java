package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {
    @PersistenceContext
    private EntityManager em;


    public Long save(Department department) {
        em.persist(department);
        return department.getId();
    }

    public List<Doctor> findBydepartname(String name){
        return em.createQuery("select d from Doctor d where d.department = :department",Doctor.class)
                .setParameter("department",name)
                .getResultList();
    }

    public Department findById(Long id) {
        return em.find(Department.class, id);
    }

}
