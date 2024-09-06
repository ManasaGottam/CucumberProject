package org.example.pages;

import lombok.RequiredArgsConstructor;
import org.example.utils.CommonScriptUtilities;
import org.example.utils.ReportUtils;
import org.example.utils.ScreenshotUtils;
import org.example.utils.SeleniumDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginPage {
    private final SeleniumDriverUtils seleniumDriverUtils;
    private By usernameField = By.name("uid");
    private By passwordField = By.name("password");
    private By loginButton = By.name("btnLogin");

    public void enterUsername(String username) {
        seleniumDriverUtils.sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        seleniumDriverUtils.sendKeys(passwordField, password);
    }

    public void clickLogin() {
        seleniumDriverUtils.clickByJavascript(loginButton);
    }
}
