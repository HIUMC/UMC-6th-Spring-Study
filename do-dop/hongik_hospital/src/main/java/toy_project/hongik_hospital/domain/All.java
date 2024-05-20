package toy_project.hongik_hospital.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class All {
    @Id @GeneratedValue
    @Column(name = "all_id")
    private Long id;

    private Long doctor_id;
    private Long hospital_id;
    private Long department_id;
    private Long patient_id;
    private LocalDateTime reserve_time;
}
