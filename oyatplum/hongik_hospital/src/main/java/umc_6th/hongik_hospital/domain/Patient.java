package umc_6th.hongik_hospital.domain;

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
    private Long patient_id;

    @Enumerated(EnumType.STRING)
    private GenderStatus gender_status;

    private int age;
    private String patient_name;

    @OneToMany(mappedBy = "patient")
    private List<Reserve> reserves = new ArrayList<>();

}
