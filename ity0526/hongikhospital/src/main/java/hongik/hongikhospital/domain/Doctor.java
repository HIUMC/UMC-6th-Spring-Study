package hongik.hongikhospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue
    @Column(name = "doctor_id")
    private Long id;

    private int career;

    private String doctorName;

    private String phoneNumber;

    @OneToMany(mappedBy = "doctor")
    private List<Reserve> reserves = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
