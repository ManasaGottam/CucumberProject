package org.example.utils;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesUtils {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/suite_data.properties";
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    @SneakyThrows
    public static Properties readProperties() {
        InputStream fileReader = new FileInputStream(FILE_PATH);
        Properties properties = new Properties();
        properties.load(fileReader);
        return properties;
    }

    public static String getPropertyValue(String propertyName) {
        Properties properties = readProperties();
        return properties.getProperty(propertyName);
    }

    private static void printPropertiesToLog(Properties properties) {
        @SuppressWarnings("unchecked") // propertyNames javadoc says ClassCastException is
        Enumeration<String> enums = (Enumeration<String>) properties.propertyNames();
        logger.info("Properties are:");
        while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            logger.info(key + " : " + properties.getProperty(key));
        }
    }
}
