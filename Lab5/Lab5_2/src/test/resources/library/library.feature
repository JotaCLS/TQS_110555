@library_sample
Feature: Library Search

  Background: Library Search
    Given I am on the library search page
    Given I have a book with the title "The RSpec book", author "David Chelimsky" and published date 2010-01-01
    Given I have a book with the title "The Cucumber book", author "Matt Wynne" and published date 2012-03-13 
    Given I have a book with the title "The Cucumber book", author "Matt Wynne" and published date 2015-10-12

      

  Scenario: Search for books published beetwen 2010-01-01 and 2016-01-01
    When I search for books published between 2010-01-01 and 2016-01-01
    Then I should see "The RSpec Book" by "David Chelimsky" published in 2010-01-01
    Then I should see "The Cucumber Book" by "Matt Wynne" published in 2012-03-13
    Then I should see "The Cucumber Book" by "Matt Wynne" published in 2015-10-12


  Scenario: Search for books published beetwen 2010-01-01 and 2012-03-13
    When I search for books published between 2010-01-01 and 2012-03-13
    Then I should see "The RSpec Book" by "David Chelimsky" published in 2010-01-01
    Then I should see "The Cucumber Book" by "Matt Wynne" published in 2012-03-13
    Then I should not see "The Cucumber Book" by "Matt Wynne" published in 2015-10-12