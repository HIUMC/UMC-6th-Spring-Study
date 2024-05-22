package hongik.hongikhospital.domain;

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
    @Column(name = "all_id")
    private Long id;

    private String doctor;

    private String hospital;

    private String department;

    private String patientName;

    private LocalDateTime time;

}
