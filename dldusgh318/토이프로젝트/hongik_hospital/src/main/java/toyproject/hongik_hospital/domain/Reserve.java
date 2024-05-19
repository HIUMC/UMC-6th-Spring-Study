package toyproject.hongik_hospital.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Reserve {

    @Id @GeneratedValue
    @Column(name = "reserve_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReserveStatus status;
    private Timestamp time;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    //생성매서드
    public static Reserve createReserve(Patient patient, Doctor doctor){
        Reserve reserve=new Reserve();
        reserve.setStatus(ReserveStatus.RESERVE);
        reserve.setTime(Timestamp.valueOf(LocalDateTime.now()));
        reserve.setPatient(patient);
        reserve.setDoctor(doctor);
        return reserve;
    }

    public void cancel(){
        if (this.getStatus()==ReserveStatus.COMPLETE){
            throw new IllegalStateException("이미 끝난 예약은 취소가 불가능합니다");
        }

        this.setStatus(ReserveStatus.CANCEL);
    }

}
