package umc_6th.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Reserve {
    @Id @GeneratedValue
    @Column(name = "reserve_id")
    private Long reserve_id;

    @Enumerated(EnumType.STRING)
    private ReserveStatus reserve_status;

    private LocalDateTime reserve_time;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;


}
