package com.spike.PatientService.controller;

import com.spike.PatientService.contract.Address;
import com.spike.PatientService.contract.Patient;
import com.spike.PatientService.service.AddressService;
import com.spike.PatientService.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    AddressService addressService;

    @Autowired
    ObservableToDeferredResult observableToDeferredResult;

    @RequestMapping("/patient")
    public DeferredResult<List<Patient>> getAllPatients(){
        Observable<Patient> allPatients = patientService.getAllPatients();
        return observableToDeferredResult.getListAsDeferredResult(allPatients);
    }

    @RequestMapping("/patient/{id}/addresses")
    public DeferredResult<List<Address>> getAddressesByPatient(@PathVariable("id") int patientId){
        Observable<Address> addressesForPatient = addressService.getAddressesForPatient(patientId);
        return observableToDeferredResult.getListAsDeferredResult(addressesForPatient);
    }

}
