Feature: Final Bill Calculation

  Scenario Outline: Customer bill amount calculation
    Given I have a customer
    And user enters initial bill amount as <InitialBillAmount>
    And sales tax rate is <TaxRate>%
    Then final bill amount is <CalculatedBillAmount>
    Examples:
      | InitialBillAmount | TaxRate | CalculatedBillAmount |
      | 100               | 10      | 110                  |
      | 200               | 12      | 224                  |
      | 500               | 14.55   | 572.75               |