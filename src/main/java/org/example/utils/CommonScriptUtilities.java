package org.example.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CommonScriptUtilities {
    private static final Logger logger = LoggerFactory.getLogger(CommonScriptUtilities.class);

    /**
     * Creates new file if it doesn't exist. Logs file content. If the file is null,
     * NullPointerException is expected.
     *
     * @param file to read content
     */
    @SneakyThrows(IOException.class)
    public void assureFileExistsAndLogContent(File file) {
        // noinspection ResultOfMethodCallIgnored
        file.getParentFile()
                .mkdirs();
        String filePath = file.getAbsolutePath();

        if (file.createNewFile()) {
            logger.info("'{}' file created.", filePath);
        } else {
            logger.info("'{}' file already exists.", filePath);
        }
        logFileContent(file);
    }

    @SneakyThrows
    public void logFileContent(File file) {
        String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        if (fileContent.isEmpty()) {
            logger.info("'{}' file is empty.", file);
        } else {
            logger.info("'{}' file has the following content:\r\n'{}'.", file, fileContent);
        }
    }

    public String getCurrentTimestamp() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return timestamp;
    }
}
