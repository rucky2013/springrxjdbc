package com.spike.PatientService.repository;

import com.github.davidmoten.rx.jdbc.Database;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.spike.PatientService.model.Patient;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
@Scope("prototype")
public class AllPatientRepositoryCommand extends HystrixCommand<List<Patient>> {

    @Autowired
    private HikariDataSource ds;

    public AllPatientRepositoryCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("PatientRepository"));
    }

    @Override
    protected List<Patient> run(){
        Database db = null;
        Connection connection = null;
        try {
            connection = ds.getConnection();
            db = Database.from(connection);
            return db.select("select id, first_name, last_name from PATIENT order by last_name")
                    .autoMap(Patient.class).toList().toBlocking().single();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                finally {
                    if (db != null)
                        db.close();
                }
        }
    }
}
