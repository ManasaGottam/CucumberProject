package org.example.tests.uitests;

import lombok.RequiredArgsConstructor;
import org.example.pages.DemoQAPage;
import org.example.utils.PropertiesUtils;
import org.example.utils.SeleniumDriverUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
@Service
@RequiredArgsConstructor
public class DemoQATest extends BaseTest {
    @Autowired
    private SeleniumDriverUtils seleniumDriverUtils;
    @Autowired
    private DemoQAPage demoQAPage;

    public void setup() {
        seleniumDriverUtils.openUrl(PropertiesUtils.getPropertyValue("demoqa.url"));
    }

    @Test
    public void navigateToElements() {
        setup();
        demoQAPage.cardByName("Elements").click();
        demoQAPage.navigateToElement("Text Box");
    }
}
