Feature: Menu Management

  Background: : Set up a menu item
    Given I have a menu item with name "Cucumber Dressing" and price 5
    When I add that menu item
    Then Menu Item with name "Cucumber Dressing" should be added

  @FunctionalTest @SmokeTest
  Scenario: Add a menu item
    Given I have a menu item with name "Cucumber Sandwich" and price 20
    When I add that menu item
#    And I have a menu item with name "Cucumber Salad" and price 10
    Then Menu Item with name "Cucumber Sandwich" should be added

  @FunctionalTest
  Scenario: Add a menu item
    Given I have a menu item with name "Cucumber Salad" and price 10
    When I add that menu item
    Then Menu Item with name "Cucumber Salad" should be added

  Scenario: Add a menu item
    Given I have a menu item with name "Cucumber Dressing" and price 5
    When I add that menu item
    Then I should see an error message with value "Duplicate Item: Cucumber Dressing"