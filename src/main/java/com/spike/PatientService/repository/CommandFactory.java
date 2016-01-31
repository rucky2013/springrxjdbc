package com.spike.PatientService.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;

abstract class CommandFactory {

    @Autowired
    protected HikariDataSource dataSource;

}
