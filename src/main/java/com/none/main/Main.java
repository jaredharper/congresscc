package com.none.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * This starts the Spring Boot application
 * 
 * @author jthomas
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.none")
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }	
}
