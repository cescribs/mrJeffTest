package com.mrjeff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by Cesar on 28/02/2019.
 */
@SpringBootApplication
public class MrJeffApplication {

    public static void main(String[] args) {
        SpringApplication.run(MrJeffApplication.class, args);
    }

}
