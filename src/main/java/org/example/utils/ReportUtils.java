package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ReportUtils {
    private final CommonScriptUtilities commonScriptUtilities;
    private final ScreenshotUtils screenshotUtils;
    private String reportDir;
    static ExtentSparkReporter htmlReport;
    static ExtentReports extentReports;
    static ExtentTest extentTest;
    String sessionName;

    public ExtentReports getInstance() {
        if (extentReports == null) {
            String curWorkingDir = System.getProperty("user.dir");
            sessionName = "Session_" + commonScriptUtilities.getCurrentTimestamp();
            reportDir = curWorkingDir + "\\reports\\" + sessionName;
            createInstance(reportDir + "\\html-report.html");
        }
        return extentReports;
    }

    @SneakyThrows
    public ExtentReports createInstance(String filePath) {
        File file = new File(filePath);
        commonScriptUtilities.assureFileExistsAndLogContent(file);
        htmlReport = new ExtentSparkReporter(file);
        htmlReport.config().setTheme(Theme.STANDARD);
        htmlReport.config().setDocumentTitle("JUnit Test Report");
        htmlReport.config().setEncoding("utf-8");
        htmlReport.config().setReportName("JUnit Test Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReport);

        return extentReports;
    }

    public void closeInstance() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    public ExtentTest createATestCase(String testcaseName) {
        extentTest = extentReports.createTest(testcaseName);
        return extentTest;
    }

    public void addTestLog(Status status, String comment) {
        String filePath = prepareScreenshot();
        extentTest.log(status, comment, MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
    }

    private String prepareScreenshot() {
        File imageFile = screenshotUtils.capturePage(reportDir);
        String fileName = imageFile.getName();
        String filePath = format("../%s/screenshots/%s", sessionName, fileName);
        return filePath;
    }
}