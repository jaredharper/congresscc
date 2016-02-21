package com.none.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.none")
public class Main {
    public static void main(String[] args) throws Exception {
    	AnnotationConfigWebApplicationContext a = new AnnotationConfigWebApplicationContext();
    	a.register(Config.class);
    	a.refresh();
        SpringApplication.run(Main.class, args);
    }	
}
