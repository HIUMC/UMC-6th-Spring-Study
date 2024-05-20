package umc_6th.hongik_hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc_6th.hongik_hospital.domain.Doctor;
import umc_6th.hongik_hospital.domain.Patient;
import umc_6th.hongik_hospital.domain.Reserve;
import umc_6th.hongik_hospital.repository.DoctorRepository;
import umc_6th.hongik_hospital.repository.PatientRepository;
import umc_6th.hongik_hospital.repository.ReserveRepository;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReserveService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ReserveRepository reserveRepository;

    @Transactional
    public Long Reserve(Long patient_id, Long doctor_id){
        Patient patient = patientRepository.findById(patient_id);
        Doctor doctor = doctorRepository.findById(doctor_id);
        return reserveRepository.save(Reserve.createReserve(patient, doctor));
    }

}
