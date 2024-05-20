package toyproject.hongik_hospital.domain;


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

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hospital hospital;

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctorList=new ArrayList<>();

    //생성메서드? 의사 명단 생성ㅇㅇ
    public static Department createDepartment(Doctor doctor){
        Department department=new Department();
        department.getDoctorList().add(doctor);
        return department;
    }
}
