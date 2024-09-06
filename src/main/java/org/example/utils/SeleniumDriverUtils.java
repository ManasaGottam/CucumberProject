package org.example.utils;

import com.aventstack.extentreports.Status;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SeleniumDriverUtils {
    private final int PAGE_LOAD_TIMEOUT = 10;
    private final int ELEMENT_DETECTION_TIMEOUT = 10;
    private static final String FOCUS_BACKGROUND_COLOR = "#c406ab";
    private static final String FOCUS_TEXT_COLOR = "#fff";
    private final WebDriver webDriver;
    private final ReportUtils reportUtils;

    public void waitForPageLoad() {
        reportUtils.addTestLog(Status.INFO, "Waiting for pageLoad...");
        new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofSeconds(2))
                .withMessage("Page is still in loading state")
                .until(s -> {
                    JavascriptExecutor js = (JavascriptExecutor) webDriver;
                    return js.executeScript("return document.readyState").equals("complete");
                });
        reportUtils.addTestLog(Status.INFO, "Page Load completed.");
    }

    @SneakyThrows
    public void openUrl(String url) {
//        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ELEMENT_DETECTION_TIMEOUT));
        webDriver.get(url);
        waitForPageLoad();
        reportUtils.addTestLog(Status.INFO, "opened URL");
    }

    public void sendKeys(By locator, String value) {
        webDriver.findElement(locator).sendKeys(value);
        reportUtils.addTestLog(Status.INFO, "Sent Keys.." + value);
    }

    public void click(By locator) {
        WebElement webElement = webDriver.findElement(locator);
        focus(webElement);
        webDriver.findElement(locator).click();
        reportUtils.addTestLog(Status.INFO, "Performed click on " + locator);
        waitForPageLoad();
    }

    public void clickByJavascript(By locator) {
        WebElement webElement = webDriver.findElement(locator);
        focus(webElement);
        executeJavaScript("arguments[0].click", webElement);
    }

    public void close() {
        webDriver.quit();
    }

    public <T> T executeJavaScript(String jsCode, Object... arguments) {
        return (T) ((JavascriptExecutor) webDriver).executeScript(jsCode, arguments);
    }

    public void focus(WebElement webElement) {
        reportUtils.addTestLog(Status.INFO, format("Focusing on ['{}'].", webElement));
        moveToElement(webElement);
        highlight(webElement);
    }

    /**
     * Scroll to element with: transition animation (parameter behavior) by default set to 'auto',
     * vertical alignment set to 'center' and horizontal alignment (parameter inline) set to
     * 'start'.
     *
     * @param webElement target element
     */
    public void moveToElement(WebElement webElement) {
        executeJavaScript("arguments[0].scrollIntoView({ block: 'center', inline: 'start' });", webElement);
    }

    public void highlight(WebElement webElement) {
        executeJavaScript(
                "arguments[0].style.backgroundColor = '"
                        + FOCUS_BACKGROUND_COLOR +
                        "'; arguments[0].style.color = '"
                        + FOCUS_TEXT_COLOR + "';",
                webElement);
    }

    public String getText(By locator) {
        WebElement webElement = webDriver.findElement(locator);
        return webElement.getText();
    }
}
