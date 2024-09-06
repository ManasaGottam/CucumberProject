package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example.pages", "org.example.config"})
@Import(AppConfig.class)
public class TestAutomationFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAutomationFrameworkApplication.class, args);
    }
}
