package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "allReserve")
@Getter @Setter
public class AllReserve {

  @Id @GeneratedValue
  @Column(name = "allReserve_id")
  private Long id;

  private String doctor;
  private String hospital;
  private String department;
  private String patientName;
  private String time;
}
