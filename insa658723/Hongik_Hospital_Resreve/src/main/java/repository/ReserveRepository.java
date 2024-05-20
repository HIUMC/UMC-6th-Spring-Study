package repository;

import domain.Reserve;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReserveRepository {

  private final EntityManager em;

  public Long save(Reserve reserve) {
    em.persist(reserve);
    return reserve.getId();
  }

  public Reserve findById(Long id) {
    return em.find(Reserve.class, id);
  }

  public List<Reserve> findAll() {
    return em.createQuery("select r from Reserve r", Reserve.class)
        .getResultList();
  }
}
