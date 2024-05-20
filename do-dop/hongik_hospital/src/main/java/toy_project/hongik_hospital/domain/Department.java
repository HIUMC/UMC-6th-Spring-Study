package toy_project.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Department {
    @Id @GeneratedValue
    @Column(name = "department_id")
    private Long id;

    private String department_name;

    @OneToMany(mappedBy = "deparment")
    private List<Doctor> doctors = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital; //환자
}
