package umc_6th.hongik_hospital.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AllReserve {
    @Id
    @GeneratedValue
    @Column(name = "all_reserve_id")
    private Long all_reserve_id;

    private String doctor_name;
    private String hospital_name;
    private String department_name;
    private String patient_name;
    private LocalDateTime reserve_time;
}
