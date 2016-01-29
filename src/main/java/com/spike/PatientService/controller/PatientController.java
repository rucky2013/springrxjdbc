package com.spike.PatientService.controller;

import com.spike.PatientService.model.Patient;
import com.spike.PatientService.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @RequestMapping("/patient")
    public DeferredResult<List<Patient>> getAllPatients(){
        Observable<Patient> allPatients = patientService.getAllPatients();
        DeferredResult<List<Patient>> patientDeferredResult = getDeferredResult(allPatients);
        return patientDeferredResult;
    }

    private DeferredResult<List<Patient>> getDeferredResult(Observable<Patient> allPatients) {
        DeferredResult<List<Patient>> patientDeferredResult = new DeferredResult<>(9000L);
        List<Patient> patients = new ArrayList<>();
        allPatients.subscribe(patient -> patients.add(patient),
                error -> patientDeferredResult.setErrorResult(error),
                () ->  patientDeferredResult.setResult(patients));

        return patientDeferredResult;
    }


}
