package com.hms.controller.medicine;

import com.hms.entity.doctor.Medicine;
import com.hms.repository.medicine.MedicineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/getMedicineById/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) throws AttributeNotFoundException
    {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Medicine not found for this id :: " + id));
        return ResponseEntity.ok(medicine);
    }

    @PutMapping("/updateMedicineById/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicine) throws AttributeNotFoundException
    {
        Medicine existingMedicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Medicine not found for this id :: " + id));
        existingMedicine.setDrugName(medicine.getDrugName());
        existingMedicine.setStock(medicine.getStock());
        medicineRepository.save(medicine);
        return ResponseEntity.ok(medicine);
    }

    @DeleteMapping("/deleteMedicineById/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteMedicine(@PathVariable Long id) throws AttributeNotFoundException
    {
        Medicine existingMedicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Medicine not found for this id :: " + id));
        medicineRepository.delete(existingMedicine);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
