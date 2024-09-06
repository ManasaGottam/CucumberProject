package org.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.example.pages", "org.example.config", "org.example.utils", "org.example.backend"})
public class AppConfig {
}

