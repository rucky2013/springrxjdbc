package com.spike.PatientService.repository;

import com.github.davidmoten.rx.jdbc.Database;
import com.netflix.hystrix.*;
import com.spike.PatientService.model.Address;
import com.spike.PatientService.model.Patient;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddressesByPatientCommand extends TemplateRepositoryCommand<List<Address>> {

    private final int patientId;

    @Autowired
    public AddressesByPatientCommand(HikariDataSource dataSource, int timeOut, int patientId) {
        super(dataSource, Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("PatientRepository"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("AddressesByPatientCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("AllPatientsPool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(timeOut)));
        this.patientId = patientId;
    }

    @Override
    protected List<Address> handle(Database database) {
        return database.select("select add.address1, add.address2, add.city, add.state, add.zip_code, add.type from address add, patientaddress padd where padd.patient_id = ? and padd.address_id = add.id")
                .parameter(patientId)
                .autoMap(Address.class).toList().toBlocking().single();
    }
}
