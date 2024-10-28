package com.upao.eduaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.upao.eduaccess.repository")
@SpringBootApplication
public class EduAccesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduAccesApplication.class, args);
    }

}
