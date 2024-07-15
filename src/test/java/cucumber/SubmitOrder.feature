
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test for Submitting the Order
    Given Log with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples: 
      | name  					| password 			|  productName |
      | yusuf@gmail.com | Yasmin@786    |ZARA COAT 3|
      
      

