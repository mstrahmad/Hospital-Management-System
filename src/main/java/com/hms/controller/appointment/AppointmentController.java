package com.hms.controller.appointment;

import com.hms.entity.doctor.Appointment;
import com.hms.repository.appointment.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
