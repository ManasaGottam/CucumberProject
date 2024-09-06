package org.example;

import lombok.RequiredArgsConstructor;
import org.example.pages.LoginPage;
import org.example.utils.PropertiesUtils;
import org.example.utils.SeleniumDriverUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonHelper {
    @Autowired
    private LoginPage loginPage;
    @Autowired
    private WebDriver driver;

    @Autowired
    private PropertiesUtils propertiesUtils;

    @Autowired
    private SeleniumDriverUtils seleniumDriverUtils;

    public void login() {
        seleniumDriverUtils.openUrl(propertiesUtils.getPropertyValue("url"));
        loginPage.enterUsername(propertiesUtils.getPropertyValue("username"));
        loginPage.enterPassword(propertiesUtils.getPropertyValue("password"));
        loginPage.clickLogin();
    }
}
