package com.example.apifinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }) simple hello*/
@SpringBootApplication
public class ApiFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiFinanceApplication.class, args);
    }

}