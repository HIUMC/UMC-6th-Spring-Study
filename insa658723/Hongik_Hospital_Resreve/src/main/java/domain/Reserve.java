package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "reserve")
@Getter @Setter
public class Reserve {

  @Id @GeneratedValue
  @Column(name = "reserve_id")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @Enumerated(EnumType.STRING)
  private ReserveStatus reserveStatus;
  private String reserveTime;

}
