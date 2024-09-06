package org.example.tests.cucumber.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class DataTableSteps {

    @Given("I placed an order for the following items")
    public void i_placed_an_order_for_the_following_items(DataTable dataTable) {
        List<Map<String, String>> billData = dataTable.asMaps();
        for (Map<String, String> order : billData) {
            for (String billItem : order.keySet()) {
                System.out.print(billItem + ": " + order.get(billItem) + "; ");
            }
            System.out.println();
        }
    }

    @When("I generate the bill")
    public void i_generate_the_bill() {

    }

    @Then("a bill for ${int} should be generated")
    public void a_bill_for_$_should_be_generated(Integer int1) {

    }
}
