package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospital")
@Getter @Setter
public class Hospital {

  @Id @GeneratedValue
  @Column(name = "hospital_id")
  private Long id;

  @OneToMany(mappedBy = "hospital")
  private List<Department> departments = new ArrayList<>();

  private String hospitalName;

  @Embedded
  private Address address;

}
