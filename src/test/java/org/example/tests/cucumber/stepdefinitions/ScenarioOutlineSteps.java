package org.example.tests.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.example.AppConfig;
import org.example.utils.ReportUtils;
import org.example.utils.SeleniumDriverUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = AppConfig.class)
public class ScenarioOutlineSteps {
    int initialBillAmount;
    double taxRate;
    @Autowired
    private SeleniumDriverUtils seleniumDriverUtils;
    @Autowired
    private ReportUtils reportUtils;

    @Given("I have a customer")
    public void i_have_a_customer() {
        System.out.println("Bill for customer: ");
        reportUtils.getInstance();
        reportUtils.createATestCase("testInfo.getDisplayName()");
        seleniumDriverUtils.openUrl("https://www.calculatorsoup.com/calculators/financial/sales-tax-calculator.php");
    }

    @Given("user enters initial bill amount as {int}")
    public void user_enters_initial_bill_amount_as(Integer initialBillAmount) {
        this.initialBillAmount = initialBillAmount;
        System.out.println("Initial Bill Amount is: " + initialBillAmount);
        seleniumDriverUtils.sendKeys(By.name("price"), Integer.toString(initialBillAmount));
    }

    @Given("sales tax rate is {double}%")
    public void sales_tax_rate_is(Double taxRate) {
        this.taxRate = taxRate;
        System.out.println("Tax Rate is: " + initialBillAmount);
        seleniumDriverUtils.sendKeys(By.name("state"), Double.toString(taxRate));
    }

    @Then("final bill amount is {double}")
    public void final_bill_amount_is(Double finalBillAmount) {
        seleniumDriverUtils.click(By.name("calculateButton"));
        String totalBillText = seleniumDriverUtils.getText(By.xpath("//div[.='Total Price with tax: ']/following-sibling::div"));
        totalBillText = totalBillText.trim().split(" ")[1];
        System.out.println("Total Bill Amount text is: " + totalBillText);
        Assertions.assertEquals(Double.parseDouble(totalBillText), finalBillAmount);
        seleniumDriverUtils.close();
        reportUtils.closeInstance();
    }
}
