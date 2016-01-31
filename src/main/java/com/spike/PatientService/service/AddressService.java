package com.spike.PatientService.service;

import com.spike.PatientService.contract.Address;
import com.spike.PatientService.repository.AddressesByPatientRepositoryCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
@Scope("prototype")
public class AddressService {

    @Autowired
    private AddressesByPatientRepositoryCommand addressesByPatientRepositoryCommand;

    public Observable<Address> getAddressesForPatient(int patientId){
        return null;
    }
}
