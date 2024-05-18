package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Patient {

  @Id @GeneratedValue
  @Column(name = "patient_id")
  private Long id;

  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
  private List<Reserve> reserves = new ArrayList<>();

  private int age;
  private boolean gender;
  private String name;

  @Embedded
  private Address address;


}
