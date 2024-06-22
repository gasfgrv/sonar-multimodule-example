package com.example.sonar.multimodule.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.sonar.multimodule")
@ComponentScan(basePackages = "com.example.sonar.multimodule")
@EnableJpaRepositories(basePackages = "com.example.sonar.multimodule")
@EntityScan(basePackages = "com.example.sonar.multimodule.entity")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
