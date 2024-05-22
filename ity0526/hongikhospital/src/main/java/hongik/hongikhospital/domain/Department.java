package hongik.hongikhospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private Long id;

    private String departName;

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

}
