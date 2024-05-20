package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.Lint;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
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

  //==생성자==//
  public Patient(int age, boolean gender, String name) {
    this.age = age;
    this.gender = gender;
    this.name = name;
  }

  protected Patient() {}
}
