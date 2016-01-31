package com.spike.PatientService.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AddressesByPatientCommandFactory extends CommandFactory {

    @Value("${AddressesByPatientCommand.timeout}")
    private int timeOut;

    public AddressesByPatientCommand createCommand(int patientId){
        return new AddressesByPatientCommand(dataSource, timeOut, patientId);
    }
}
