package com.spike.PatientService.service;

import com.spike.PatientService.contract.Address;
import com.spike.PatientService.contract.Order;
import com.spike.PatientService.contract.Patient;
import com.spike.PatientService.contract.Refill;
import com.spike.PatientService.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Observable<Patient> getAllPatients(){
        return patientRepository.getAllPatients().map(p -> mapPatient(p));
    }

    private Patient mapPatient(com.spike.PatientService.model.Patient patient) {
        return new Patient(patient.id(), patient.firstName(), patient.lastName(), null);
    }

}
