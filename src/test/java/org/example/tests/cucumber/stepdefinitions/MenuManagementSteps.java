package org.example.tests.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.cucumber.RestaurantMenu;
import org.example.cucumber.RestaurantMenuItem;
import org.junit.jupiter.api.Assertions;

public class MenuManagementSteps {
    RestaurantMenuItem newMenuItem;
    RestaurantMenu menu = new RestaurantMenu();
    String errorMessage;

    @Given("I have a menu item with name {string} and price {int}")
    public void iHaveAMenuItemWithNameAndPrice(String menuItemName, int itemPrice) {
        newMenuItem = new RestaurantMenuItem(menuItemName, menuItemName, itemPrice);
    }

    @When("I add that menu item")
    public void iAddThatMenuItem() {
        try {
            menu.addMenuItem(newMenuItem);
        } catch (IllegalArgumentException exception) {
            errorMessage = exception.getMessage();
        }
    }

    @Then("Menu Item with name {string} should be added")
    public void menuItemWithNameShouldBeAdded(String menuItemName) {
        boolean itemExists = menu.isItemPresentInMenu(menuItemName);
        Assertions.assertTrue(itemExists);
    }

    @Then("I should see an error message with value {string}")
    public void iShouldSeeAnErrorMessageWithValue(String message) {
        Assertions.assertEquals(message, errorMessage);
    }
}
