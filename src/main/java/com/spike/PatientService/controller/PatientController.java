package com.spike.PatientService.controller;

import com.spike.PatientService.model.Patient;
import com.spike.PatientService.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @RequestMapping("/patient")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }


}
