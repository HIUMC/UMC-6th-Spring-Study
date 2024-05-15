package umc_6th.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "doctors")
@Getter @Setter
public class Doctor {
    @Id @GeneratedValue
    @Column(name = "doctor_id")
    private Long doctor_id;

    private int career;
    private String doctor_name;
    private String phoneNum;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "doctor")
    private List<Reserve> reserves = new ArrayList<>();

}
