package org.example.config;

import lombok.SneakyThrows;
import org.example.utils.PropertiesUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.lang.String.format;

@Configuration
public class WebDriverConfig {

    @Bean
    @SneakyThrows
    public WebDriver webDriver() {
        WebDriver driver;
        String browserType = PropertiesUtils.getPropertyValue("browserType");
        if (browserType.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else if (browserType.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new Exception(format("Invalid BrowserType %s", browserType));
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }
}

