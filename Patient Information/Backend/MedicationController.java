package com.example.demo.controller;

import com.example.demo.entity.Medication;
import com.example.demo.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @GetMapping
    public String listMedications(Model model) {
        model.addAttribute("medications", medicationService.getAllMedications());
        return "medications";
    }

    @GetMapping("/new")
    public String createMedicationForm(Model model) {
        model.addAttribute("medication", new Medication());
        return "create_medication";
    }

    @PostMapping
    public String saveMedication(@ModelAttribute("medication") Medication medication) {
        medicationService.saveMedication(medication);
        return "redirect:/medications";
    }

    @GetMapping("/edit/{id}")
    public String editMedicationForm(@PathVariable Long id, Model model) {
        model.addAttribute("medication", medicationService.getMedicationById(id));
        return "edit_medication";
    }

    @PostMapping("/{id}")
    public String updateMedication(@PathVariable Long id, @ModelAttribute("medication") Medication medication) {
        Medication existingMedication = medicationService.getMedicationById(id);
        existingMedication.setName(medication.getName());
        existingMedication.setDosage(medication.getDosage());
        existingMedication.setFrequency(medication.getFrequency());
        existingMedication.setStartDate(medication.getStartDate());
        existingMedication.setEndDate(medication.getEndDate());
        existingMedication.setDescription(medication.getDescription());
        medicationService.saveMedication(existingMedication);
        return "redirect:/medications";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return "redirect:/medications";
    }
}
