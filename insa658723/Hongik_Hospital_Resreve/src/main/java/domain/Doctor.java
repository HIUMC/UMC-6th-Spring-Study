package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "doctor")
@Getter @Setter
public class Doctor {

  @Id @GeneratedValue
  @Column(name = "doctor_id")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "department_id")
  private Department department;

  @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
  private List<Reserve> reserves = new ArrayList<>();

  private int career;
  private String doctorName;
  private String phoneNumber;

}
