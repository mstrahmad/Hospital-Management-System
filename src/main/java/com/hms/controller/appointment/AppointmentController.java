package com.hms.controller.appointment;

import com.hms.entity.doctor.Appointment;
import com.hms.repository.appointment.AppointmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AppointmentController
{
    private AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping("/bookAppointment")
    public Appointment createAppointment(@RequestBody Appointment appointment)
    {
        return appointmentRepository.save(appointment);
    }

    @GetMapping("/getAllAppointments")
    public List<Appointment> getAllAppointments()
    {
        return appointmentRepository.findAll();
    }

    @DeleteMapping("/deleteAppointments/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteAppointment(@PathVariable Long id) throws AttributeNotFoundException{

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Resource Id Not Found: " + id));

        appointmentRepository.delete(appointment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
