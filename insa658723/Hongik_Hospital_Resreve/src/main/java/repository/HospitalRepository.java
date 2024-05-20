package repository;

import domain.Hospital;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HospitalRepository {

  private final EntityManager em;

  public Long save(Hospital hospital) {
    em.persist(hospital);
    return hospital.getId();
  }

}
