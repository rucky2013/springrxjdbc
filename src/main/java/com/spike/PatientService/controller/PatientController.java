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

    @Value("${response.timeout}")
    private long timeOut;

    @RequestMapping("/patient")
    public DeferredResult<List<Patient>> getAllPatients(){
        Observable<Patient> allPatients = patientService.getAllPatients();
        DeferredResult<List<Patient>> patientDeferredResult = getDeferredResult(allPatients);
        return patientDeferredResult;
    }

    @RequestMapping("/patient/{id}/addresses")
    public DeferredResult<List<Address>> getAddressesByPatient(@PathVariable("id") int patientId){
        Observable<Address> addressesForPatient = addressService.getAddressesForPatient(patientId);
        DeferredResult<List<Address>> addressesDeferredResult = getAddressesDeferredResult(addressesForPatient);
        return addressesDeferredResult;
    }

    private DeferredResult<List<Address>> getAddressesDeferredResult(Observable<Address> addressesForPatient) {
        DeferredResult<List<Address>> deferredResult = new DeferredResult<>(timeOut);
        List<Address> addresses = new ArrayList<>();
        addressesForPatient.subscribe(add -> addresses.add(add),
                error -> deferredResult.setErrorResult(error),
                () -> deferredResult.setResult(addresses));
        return deferredResult;
    }

    private DeferredResult<List<Patient>> getDeferredResult(Observable<Patient> allPatients) {
        DeferredResult<List<Patient>> patientDeferredResult = new DeferredResult<>(timeOut);
        List<Patient> patients = new ArrayList<>();
        allPatients.subscribe(patient -> patients.add(patient),
                error -> patientDeferredResult.setErrorResult(error),
                () ->  patientDeferredResult.setResult(patients));

        return patientDeferredResult;
    }


}
