@blaze_sample
Scenario: Buying a trip~

  Given I am on the Blaze Demo site
  When I select the departure city as "Boston"
  And I select the destination city as "London"
  And I click on the Find Flights button
  And I choose the first flight
  And I fill in the form with the following information:
    | Name        | "John Doe" |
    | Address     | "123 Main St" |
    | City        | "Boston" |
    | State       | "MA" |
    | Zip Code    | "02101" |
    | Credit Card | "1234567890" |
    | Month       | "12" |
    | Year        | "2020" |
    | Name on Card| "John Doe" |
  And I click on the Purchase Flight button
  Then I should see the message "Thank you for your purchase today!"