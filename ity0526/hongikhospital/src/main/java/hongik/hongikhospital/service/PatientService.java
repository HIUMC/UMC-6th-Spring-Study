package hongik.hongikhospital.service;

import hongik.hongikhospital.domain.Patient;
import hongik.hongikhospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PatientService {
    @Autowired
    PatientRepository patientRepository;


}
