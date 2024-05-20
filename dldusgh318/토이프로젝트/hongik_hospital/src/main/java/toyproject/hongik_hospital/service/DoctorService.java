package toyproject.hongik_hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.hongik_hospital.domain.Doctor;
import toyproject.hongik_hospital.repository.DoctorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public List<Doctor> findDoctors(){return doctorRepository.findAll();}
    public Doctor findOne(Long doctorId){return doctorRepository.findOne(doctorId);}
}
