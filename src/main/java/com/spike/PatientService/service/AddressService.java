package com.spike.PatientService.service;

import com.spike.PatientService.contract.Address;
import com.spike.PatientService.repository.AddressesByPatientCommand;
import com.spike.PatientService.repository.AddressesByPatientCommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressesByPatientCommandFactory commandFactory;

    public Observable<Address> getAddressesForPatient(int patientId){
        AddressesByPatientCommand command = commandFactory.createCommand(patientId);
        List<com.spike.PatientService.model.Address> addresses = command.execute();
        return Observable.from(addresses).map(add -> mapAddress(add));
    }

    private Address mapAddress(com.spike.PatientService.model.Address addressModel) {
        Address address = new Address();
        address.setAddress1(addressModel.address1());
        address.setAddress2(addressModel.address2());
        address.setCity(addressModel.city());
        address.setZipCode(addressModel.zipCode());
        address.setState(addressModel.state());
        address.setType(addressModel.type());
        return address;
    }
}
