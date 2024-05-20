package toyproject.hongik_hospital;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import toyproject.hongik_hospital.domain.Patient;
import toyproject.hongik_hospital.domain.PatientGender;
import toyproject.hongik_hospital.repository.PatientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    PatientRepository patientRepository;

    @Test
    @Rollback(value = false)
    public void 환자() throws Exception{
        Patient patient=new Patient();
        patient.setName("이연호");
        patient.setAge(22);
        patient.setGender(PatientGender.FEMALE);
        patientRepository.save(patient);
    }

}
