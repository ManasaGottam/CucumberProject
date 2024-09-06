package org.example.tests.cucumber.testrunners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectDirectories;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/java/org/example/tests/cucumber/features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:reports/cucumber-reports/cucumberReport.html")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@ApiTest")
public class ApiTests {
}
