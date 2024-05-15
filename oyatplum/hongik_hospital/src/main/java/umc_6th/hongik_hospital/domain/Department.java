package umc_6th.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "departments")
@Getter @Setter
public class Department {

    @Id @GeneratedValue
    private Long department_id;

    private String department_name;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "dapartment")
    private List<Doctor> doctors = new ArrayList<>();

}
