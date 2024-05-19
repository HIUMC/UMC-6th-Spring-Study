package toyproject.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Patient {

    @Id
    @GeneratedValue
    @Column(name = "patient_id")
    private Long id;
    private int age;

    @Enumerated(EnumType.STRING)
    private PatientGender gender;
    private String username;

    @OneToMany(mappedBy = "patient")
    private List<Reserve> reserveList = new ArrayList<>();
}
