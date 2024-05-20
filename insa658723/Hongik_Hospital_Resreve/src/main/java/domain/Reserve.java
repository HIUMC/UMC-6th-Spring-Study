package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.print.Doc;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "reserve")
@Getter @Setter
public class Reserve {

  @Id @GeneratedValue
  @Column(name = "reserve_id")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @Enumerated(EnumType.STRING)
  private ReserveStatus reserveStatus;
  private LocalDateTime reserveTime;

  //==연관관계 메서드==//
  public void setPatient(Patient patient) {
    this.patient = patient;
    patient.getReserves().add(this);
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
    doctor.getReserves().add(this);
  }

  //==생성메서드==/
  public static Reserve createReserve(Patient patient, Doctor doctor) {
    Reserve reserve = new Reserve();
    reserve.setPatient(patient);
    reserve.setDoctor(doctor);
    reserve.setReserveStatus(ReserveStatus.RESERVE);
    reserve.reserveTime = LocalDateTime.now();
    return reserve;
  }

  public Long cancel() {
    this.setReserveStatus(ReserveStatus.CANCEL);
    patient.getReserves().remove(this);
    return this.getId();
  }
}
