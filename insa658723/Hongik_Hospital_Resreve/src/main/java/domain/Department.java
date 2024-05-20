package domain;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "department")
@Getter @Setter
public class Department {

  @Id @GeneratedValue
  @Column(name = "department_id")
  private Long id;

  @OneToMany(mappedBy = "department")
  private List<Doctor> doctors = new ArrayList<>();

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "hospital_id")
  private Hospital hospital;

  private String departmentName;

  //==생성자==//
  public Department(Hospital hospital, String departmentName) {
    this.hospital = hospital;
    this.departmentName = departmentName;
  }

  protected Department(){}

  //==생성메서드==//
  public static Department createDepartment(Doctor doctor) {
    Department department = new Department();
    department.getDoctors().add(doctor);
    return department;
  }
}
