package umc_6th.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import umc_6th.hongik_hospital.repository.ReserveRepository;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Reserve {
    @Id @GeneratedValue
    @Column(name = "reserve_id")
    private Long reserve_id;

    @Enumerated(EnumType.STRING)
    private ReserveStatus reserve_status;

    private LocalDateTime reserve_time;

    @ManyToOne(fetch = LAZY,cascade =  CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = LAZY,cascade =  CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    public void setPatient(Patient patient){
        this.patient = patient;
        patient.getReserves().add(this);
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
        doctor.getReserves().add(this);
    }

    public static Reserve createReserve(Patient patient, Doctor doctor){
        Reserve reserve = new Reserve();
        reserve.setPatient(patient);
        reserve.setDoctor(doctor);
        reserve.setReserve_status(ReserveStatus.RESERVE);
        reserve.setReserve_time(LocalDateTime.now());
        return reserve;
    }

}
