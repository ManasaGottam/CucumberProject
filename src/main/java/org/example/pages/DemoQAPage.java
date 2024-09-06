package org.example.pages;

import lombok.RequiredArgsConstructor;
import org.example.utils.SeleniumWebElement;
import org.example.utils.WebElementsHelper;
import org.openqa.selenium.By;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DemoQAPage {
    private final WebElementsHelper webElementsHelper;

    public SeleniumWebElement cardByName(String cardName) {
        return webElementsHelper.seleniumWebElement(By.xpath(format("//h5[text()='%s']/ancestor::div[contains(@class,'card-body')]", cardName)));
    }

    public SeleniumWebElement element(String elementName) {
        return webElementsHelper.seleniumWebElement(By.xpath(format("//li[.='%s']", elementName)));
    }

    public void navigateToElement(String elementName) {
        element(elementName).click();
    }
}
