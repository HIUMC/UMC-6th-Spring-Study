package hongik.hongikhospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue
    @Column(name = "patient_id")
    private Long id;

    private int age;

    private String gender;

    private String patientName;

    @OneToMany(mappedBy = "patient")
    private List<Reserve> reserves = new ArrayList<>();

}
