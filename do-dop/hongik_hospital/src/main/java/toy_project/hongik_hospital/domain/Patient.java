package toy_project.hongik_hospital.domain;

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

    private String patient_name;

    private String patient_gender;

    private int patient_age;

    @OneToMany(mappedBy = "patient")
    private List<Reserve> reserves = new ArrayList<>();
}
