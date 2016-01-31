package com.spike.PatientService.repository;

import com.github.davidmoten.rx.jdbc.Database;
import com.netflix.hystrix.HystrixCommand;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class TemplateRepositoryCommand<T> extends HystrixCommand<T> {

    private HikariDataSource dataSource;

    @Autowired
    public TemplateRepositoryCommand(HikariDataSource dataSource, Setter setter) {
        super(setter);
        this.dataSource = dataSource;
    }

    @Override
    protected T run(){
        Database db = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            db = Database.from(connection);
            return handle(db);
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

    protected abstract T handle(Database database);
}
