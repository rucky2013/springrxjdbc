package com.spike.PatientService.service;

import com.spike.PatientService.contract.Patient;
import com.spike.PatientService.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Observable<Patient> getAllPatients(){
        return patientRepository.getAllPatients().map(p -> mapPatient(p));
    }

    private Patient mapPatient(com.spike.PatientService.model.Patient patientModel) {
        Patient patient = new Patient();
        patient.setId(patientModel.id());
        patient.setFirstName(patientModel.firstName());
        patient.setLastName(patientModel.lastName());
        return patient;
    }

}
