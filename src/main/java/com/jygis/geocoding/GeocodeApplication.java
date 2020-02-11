package com.jygis.geocoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GeocodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeocodeApplication.class, args);
    }

}
