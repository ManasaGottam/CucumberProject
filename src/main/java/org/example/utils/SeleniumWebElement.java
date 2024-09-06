package org.example.utils;

import com.aventstack.extentreports.Status;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Getter
public class SeleniumWebElement {
    protected final WebDriver driver;
    protected final SeleniumDriverUtils seleniumDriverUtils;
    protected final ReportUtils reportUtils;
    private By by;

    public SeleniumWebElement(By by, WebDriver driver, SeleniumDriverUtils seleniumDriverUtils, ReportUtils reportUtils) {
        this.driver = driver;
        this.seleniumDriverUtils = seleniumDriverUtils;
        this.reportUtils = reportUtils;
        this.by = by;
    }

    public WebElement getWebElement() {
        return driver.findElement(by);
    }

    public void click() {
        WebElement element = getWebElement();
        seleniumDriverUtils.focus(element);
        element.click();
        reportUtils.addTestLog(Status.INFO, "Performed click on " + element);
        seleniumDriverUtils.waitForPageLoad();
    }

    public void sendKeys(String keys) {
        getWebElement().sendKeys(keys);
    }

    public String getText() {
        return getWebElement().getText();
    }

    public boolean isDisplayed() {
        return getWebElement().isDisplayed();
    }
}
