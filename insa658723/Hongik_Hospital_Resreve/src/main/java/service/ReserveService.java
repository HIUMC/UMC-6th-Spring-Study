package service;

import domain.Department;
import domain.Doctor;
import domain.Patient;
import domain.Reserve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.DoctorRepository;
import repository.PatientRepository;
import repository.ReserveRepository;

@Service
@RequiredArgsConstructor
public class ReserveService {

  private final PatientRepository patientRepository;
  private final DoctorRepository doctorRepository;
  private final ReserveRepository reserveRepository;

  //예약생성
  public Long reserveCreate(Long patientId, Long doctorId) {
    //엔티티 조회
    Patient patient = patientRepository.findById(patientId);
    Doctor doctor = doctorRepository.findById(doctorId);

    //예약 저장
    return reserveRepository.save(Reserve.createReserve(patient, doctor));
  }

  //예약조회
  public Reserve reserveRead(Long reserveId) {
    return reserveRepository.findById(reserveId);
  }

  //예약수정

  //예약삭제
  public Long cancelReserve(Long reserveId) {
    //예약 엔티티 조회
    Reserve reserve = reserveRepository.findById(reserveId);
    return reserve.cancel();
  }
}
