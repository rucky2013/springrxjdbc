package com.spike.PatientService.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AllPatientCommandFactory extends CommandFactory {

    @Value("${allPatientCommand.timeout}")
    private int timeOut;

    public AllPatientCommand createCommand(){
        return new AllPatientCommand(dataSource, timeOut);
    }
}
