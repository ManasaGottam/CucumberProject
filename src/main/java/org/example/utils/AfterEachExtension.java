package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.stereotype.Service;

@Service
public class AfterEachExtension implements AfterEachCallback {
    private static ExtentReports extent;

    public static void setExtent(ExtentReports extent) {
        AfterEachExtension.extent = extent;
    }

    @Override
    public void afterEach(ExtensionContext context) {
        var test = extent.createTest(context.getDisplayName());
        context.getExecutionException().ifPresent(value -> test.log(Status.FAIL, value));
    }
}