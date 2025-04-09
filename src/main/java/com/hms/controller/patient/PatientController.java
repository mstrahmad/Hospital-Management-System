package com.hms.controller.patient;

import com.hms.entity.patient.Patient;
import com.hms.repository.patient.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PatientController
{
    public PatientController(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }

    private final PatientRepository patientRepository;

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

    @GetMapping("/getPatient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) throws AttributeNotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Patient Id Not Found With This ID: " + id));
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePatient(@PathVariable Long id) {
        Map<String, Boolean> response = new HashMap<>();
        try
        {
            patientRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Patient Id Not Found With This ID: " + id));
            patientRepository.deleteById(id);
            response.put("Deleted", Boolean.TRUE);
        }
        catch (AttributeNotFoundException e)
        {
            response.put(e.getMessage(), Boolean.FALSE);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient updatedPatient;
        try
        {
            Patient existingPatient = patientRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Patient Id Not Found With This ID: " + id));
            existingPatient.setId(patient.getId());
            existingPatient.setName(patient.getName());
            existingPatient.setAge(patient.getAge());
            existingPatient.setBlood(patient.getBlood());
            existingPatient.setPrescription(patient.getPrescription());
            existingPatient.setDose(patient.getDose());
            existingPatient.setFees(patient.getFees());
            existingPatient.setUrgency(patient.getUrgency());

            updatedPatient = patientRepository.save(existingPatient);

        }
        catch (AttributeNotFoundException e)
        {
            Patient exceptionPatient = new Patient();
            return ResponseEntity.ok(exceptionPatient);
        }
        return ResponseEntity.ok(updatedPatient);
    }
}
