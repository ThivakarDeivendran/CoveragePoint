Feature: Amazon Add to cart Feature
  Verify the Add to Cart Feature functionality

  Scenario: To check whether Men SubModule displays under Fashion module
    Given User launch the browser
    And User Navigate to Amazon Application
    And User verify the Amazon Application title
    When User click the Fashion Module in Amazon Main Page
    Then User Observe that Mens Clothing subModule Link displays

  Scenario: To check whether filter working properly or not
    Given User Click Average Customer Review
    And user select the price Filters
    And user select the brands "Puma","Allen Solly"

  Scenario: To check whether selected Product display or not in addToCart module
    Given User select first page and print the count of the products
    And User click the "2" product
    Then User observe that selected product displays in addToCart Module
    And User validate the number of Cart increased by 1
    And User close the browser
