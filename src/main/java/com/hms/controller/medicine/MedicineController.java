package com.hms.controller.medicine;

import com.hms.entity.doctor.Medicine;
import com.hms.repository.medicine.MedicineRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MedicineController
{
    private MedicineRepository medicineRepository;

    public MedicineController(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @PostMapping("/buyMedicine")
    public Medicine createMedicine(@RequestBody Medicine medicine)
    {
        return medicineRepository.save(medicine);
    }

    @GetMapping("/getAllMedicines")
    public List<Medicine> getAllMedicines()
    {
        return medicineRepository.findAll();
    }
}
