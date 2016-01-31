package com.spike.PatientService;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
public class MainApplication {

    @Value("${dataSourceClassName}")
    private String dataSourceClassName;

    @Value("${dataSource.user}")
    private String dataSourceUser;

    @Value("${dataSource.password}")
    private String dataSourcePassword;

    @Value("${dataSource.databaseName}")
    private String dataSourceDb;

    @Bean
    public HikariDataSource getDataSource(){
        Properties props = new Properties();
        props.setProperty("dataSourceClassName", dataSourceClassName);
        props.setProperty("dataSource.user", dataSourceUser);
        props.setProperty("dataSource.password", dataSourcePassword);
        props.setProperty("dataSource.databaseName", dataSourceDb);
        HikariConfig config = new HikariConfig(props);
        return new HikariDataSource(config);
    }

    @Bean
    public ServletRegistrationBean hystrixStreamServlet(){
        return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
