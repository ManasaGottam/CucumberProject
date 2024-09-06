package org.example.tests.uitests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.pages.LoginPage;
import org.example.utils.AfterEachExtension;
import org.example.utils.ReportUtils;
import org.example.utils.ScreenshotUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    @Autowired
    WebDriver driver;
    @Autowired
    LoginPage loginPage;
    @Autowired
    ReportUtils reportUtils;
    @Autowired
    ScreenshotUtils screenshotUtils;
    static ExtentReports extentReports;
    static ExtentTest extentTest;

    @RegisterExtension
    @Autowired
    private AfterEachExtension afterEachExtension;


    @BeforeAll
    public void setup(TestInfo testInfo) {
        extentReports = reportUtils.getInstance();
        extentTest = reportUtils.createATestCase(testInfo.getDisplayName());
    }

    @AfterEach
    public void postTestAction() {
        reportUtils.addTestLog(Status.INFO, "in AfterEach method");
        AfterEachExtension.setExtent(extentReports);
    }

    @AfterAll
    public void tearDown() {
//        seleniumDriverUtils.close();
        reportUtils.closeInstance();
    }
}
