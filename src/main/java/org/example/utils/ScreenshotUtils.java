package org.example.utils;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class ScreenshotUtils {
    private final WebDriver driver;

    @SneakyThrows
    private void takeScreenshot(File imgFile) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File tempFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.moveFile(tempFile, imgFile);
    }

    public File capturePage(String reportDir) {
        long currentTimeStamp = System.currentTimeMillis();
        String imgName = "Screenshot_" + currentTimeStamp + ".jpeg";
        String screenshotsFilePath = reportDir + "\\screenshots\\" + imgName;
        File imgFile = new File(screenshotsFilePath);
        takeScreenshot(imgFile);
        return imgFile;
    }
}
