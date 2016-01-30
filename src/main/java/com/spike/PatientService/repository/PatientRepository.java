package com.spike.PatientService.repository;

import com.github.davidmoten.rx.jdbc.Database;
import com.spike.PatientService.model.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import rx.Observable;

@Repository
public class PatientRepository {

    @Value("${db.connection}")
    private String dbConnectionString;

    @Value("${db.username}")
    private String dbUserName;

    @Value("${db.password}")
    private String dbPassword;

    public Observable<Patient> getAllPatients(){
        Database db = Database.from(dbConnectionString, dbUserName, dbPassword);
        return db.select("select id, first_name, last_name from PATIENT order by last_name")
                .autoMap(Patient.class);
    }
}
