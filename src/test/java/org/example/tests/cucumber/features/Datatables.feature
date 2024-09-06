Feature: Data Tables

  @FunctionalTest
  Scenario: Bill Amount Generation
    Given I placed an order for the following items
      | Name              | Units | Price |
      | Cucumber Sandwich | 2     | 20    |
      | Cucumber Salad    | 5     | 10    |
    When I generate the bill
    Then a bill for $90 should be generated