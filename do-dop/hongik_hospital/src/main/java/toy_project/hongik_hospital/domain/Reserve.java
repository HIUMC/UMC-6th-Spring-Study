package toy_project.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserves")
@Getter @Setter
public class Reserve {
    @Id @GeneratedValue
    @Column(name = "reserve_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReserveStatus status; //예약상태

    private LocalDateTime reserveDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient; //환자

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor; //의사
}
