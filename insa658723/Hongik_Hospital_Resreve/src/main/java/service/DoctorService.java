package service;

import domain.Department;
import domain.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.DepartmentRepository;
import repository.DoctorRepository;

@Service
@RequiredArgsConstructor
public class DoctorService {
  private final DoctorRepository doctorRepository;
  private final DepartmentRepository departmentRepository;

  public Doctor createDoctor(Long doctorId, Long departmentId) {
    Doctor doctor = doctorRepository.findById(doctorId);
    Department department = departmentRepository.findById(departmentId);
  }
}
