package com.spike.PatientService.service;

import com.spike.PatientService.contract.Patient;
import com.spike.PatientService.repository.AllPatientRepositoryCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

@Service
@Scope("prototype")
public class PatientService {

    @Autowired
    private AllPatientRepositoryCommand allPatientRepositoryCommand;

    public Observable<Patient> getAllPatients(){
        List<com.spike.PatientService.model.Patient> patients = allPatientRepositoryCommand.execute();
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
