package org.example.utils;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebElementsHelper {
    private final WebDriver driver;
    private final SeleniumDriverUtils seleniumDriverUtils;
    private final ReportUtils reportUtils;

    public SeleniumWebElement seleniumWebElement(By by) {
        return new SeleniumWebElement(by, driver, seleniumDriverUtils, reportUtils);
    }
}
