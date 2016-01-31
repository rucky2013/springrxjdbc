package com.spike.PatientService.repository;

import com.github.davidmoten.rx.jdbc.Database;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.spike.PatientService.model.Patient;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AllPatientCommand extends TemplateRepositoryCommand<List<Patient>> {

    @Autowired
    public AllPatientCommand(HikariDataSource dataSource, int timeOut) {
        super(dataSource, Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("PatientRepository"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("AllPatientsCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("AllPatientsPool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(timeOut)));
    }

    @Override
    protected List<Patient> handle(Database database) {
        return database.select("select id, first_name, last_name from PATIENT order by last_name")
                .autoMap(Patient.class).toList().toBlocking().single();
    }
}
