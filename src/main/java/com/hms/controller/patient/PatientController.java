package com.hms.controller.patient;

import com.hms.entity.patient.Patient;
import com.hms.repository.patient.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PatientController
{
    public PatientController(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }

    private PatientRepository patientRepository;

    @PostMapping("/createPatient")
    public Patient createPatient(@RequestBody Patient patient)
    {
        return patientRepository.save(patient);
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients()
    {
        return patientRepository.findAll();
    }
}
