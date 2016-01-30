package com.spike.PatientService.repository;

import com.github.davidmoten.rx.jdbc.Database;
import com.spike.PatientService.model.Patient;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import rx.Observable;

import java.sql.SQLException;

@Repository
public class PatientRepository {

    @Autowired
    private HikariDataSource ds;

    @Value("${db.connection}")
    private String dbConnectionString;

    @Value("${db.username}")
    private String dbUserName;

    @Value("${db.password}")
    private String dbPassword;

    public Observable<Patient> getAllPatients(){
        try {
            Database db;
            db = Database.from(ds.getConnection());
            return db.select("select id, first_name, last_name from PATIENT order by last_name")
                    .autoMap(Patient.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Observable.empty();
    }
}
