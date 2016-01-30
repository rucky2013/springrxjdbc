package com.spike.PatientService.repository;

import com.github.davidmoten.rx.jdbc.Database;
import com.spike.PatientService.model.Patient;
import org.springframework.stereotype.Repository;
import rx.Observable;

@Repository
public class PatientRepository {

    public Observable<Patient> getAllPatients(){
        Database db = Database.from("jdbc:postgresql://127.0.0.1:5432/dev", "dev", "dev");
        return db.select("select id, first_name, last_name from PATIENT order by last_name")
                .autoMap(Patient.class);
    }
}
