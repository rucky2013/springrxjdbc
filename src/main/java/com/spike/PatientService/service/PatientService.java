package com.spike.PatientService.service;

import com.spike.PatientService.contract.Patient;
import com.spike.PatientService.repository.AllPatientCommand;
import com.spike.PatientService.repository.AllPatientCommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private AllPatientCommandFactory commandFactory;

    public Observable<Patient> getAllPatients(){
        AllPatientCommand allPatientCommand = commandFactory.createCommand();
        List<com.spike.PatientService.model.Patient> patients = allPatientCommand.execute();
        return Observable.from(patients).map(p -> mapPatient(p));
    }

    private Patient mapPatient(com.spike.PatientService.model.Patient p) {
        Patient patient = new Patient();
        patient.setId(p.id());
        patient.setFirstName(p.firstName());
        patient.setLastName(p.lastName());
        return patient;
    }


}
