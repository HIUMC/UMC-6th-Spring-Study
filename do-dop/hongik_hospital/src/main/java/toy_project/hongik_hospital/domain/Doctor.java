package toy_project.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Doctor {
    @Id @GeneratedValue
    @Column(name = "doctor_id")
    private Long id;

    private String doctor_name;

    private String doctor_number;

    private int doctor_carrer;

    @OneToMany(mappedBy = "doctor")
    private List<Reserve> reserves = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department; //환자
}
