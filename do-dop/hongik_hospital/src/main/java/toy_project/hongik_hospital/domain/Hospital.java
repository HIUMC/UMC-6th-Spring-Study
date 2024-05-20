package toy_project.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Hospital {
    @Id @GeneratedValue
    @Column(name = "hospital_id")
    private Long id;

    private String hospital_name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "hospital")
    private List<Department> departments = new ArrayList<>();
}
