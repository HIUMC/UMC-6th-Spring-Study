package toyproject.hongik_hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.hongik_hospital.domain.AllReserve;
import toyproject.hongik_hospital.domain.Doctor;
import toyproject.hongik_hospital.domain.Patient;
import toyproject.hongik_hospital.domain.Reserve;
import toyproject.hongik_hospital.repository.AllRepository;
import toyproject.hongik_hospital.repository.DoctorRepository;
import toyproject.hongik_hospital.repository.PatientRepository;
import toyproject.hongik_hospital.repository.ReserveRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AllRepository allRepository;

    //예약
    public Long reserve(Long patientId,Long doctorId){

        Patient patient= patientRepository.findOne(patientId);
        Doctor doctor=doctorRepository.findOne(doctorId);

        //의사, 환자 설정
        Reserve reserve=Reserve.createReserve(patient,doctor);

        reserveRepository.save(reserve);
        return reserve.getId();
    }

    //예약 취소
    public void cancelReserve(Long reserveId){
        Reserve reserve=reserveRepository.findOne(reserveId);

        reserve.cancel();
    }

    public void create(Reserve reserve, Doctor doctor){
        AllReserve allReserve=new AllReserve();
        allReserve.setTime(reserve.getTime());
        allReserve.setDoctor(doctor.getName());
        allReserve.setPatient(reserve.getPatient().getName());
        allReserve.setHospital(doctor.getDepartment().getHospital().getName());
        allReserve.setDepartment(doctor.getDepartment().getName());

        allRepository.save(allReserve);
    }
}
