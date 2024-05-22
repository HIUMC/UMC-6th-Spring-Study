package hongik.hongikhospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reserve {
    @Id
    @GeneratedValue
    @Column(name = "reserve_id")
    private Long id;

    private String reserveStatus;

    private LocalDateTime reserveTime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
