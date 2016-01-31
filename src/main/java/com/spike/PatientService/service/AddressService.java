package com.spike.PatientService.service;

import com.spike.PatientService.contract.Address;
import com.spike.PatientService.repository.AddressesByPatientCommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class AddressService {

    @Autowired
    private AddressesByPatientCommandFactory commandFactory;

    public Observable<Address> getAddressesForPatient(int patientId){
        return null;
    }
}
