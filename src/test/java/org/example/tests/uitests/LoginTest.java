package org.example.tests.uitests;

import com.aventstack.extentreports.Status;
import lombok.RequiredArgsConstructor;
import org.example.pages.LoginPage;
import org.example.utils.PropertiesUtils;
import org.example.utils.SeleniumDriverUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Service
@RequiredArgsConstructor
public class LoginTest extends BaseTest {
    @Autowired
    private LoginPage loginPage;
    @Autowired
    private WebDriver driver;
    @Autowired
    private SeleniumDriverUtils seleniumDriverUtils;

    @Test
    public void testSuccessfulLogin() {
        seleniumDriverUtils.openUrl(PropertiesUtils.getPropertyValue("url"));
        reportUtils.addTestLog(Status.INFO, "Performing Login");
        loginPage.enterUsername(PropertiesUtils.getPropertyValue("username"));
        loginPage.enterPassword(PropertiesUtils.getPropertyValue("password"));
        loginPage.clickLogin();

        // Add assertions to verify successful login
        String expectedTitle = "Guru99 Bank Home Page";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
        reportUtils.addTestLog(Status.INFO, "Login SUCCESS");
    }
}

