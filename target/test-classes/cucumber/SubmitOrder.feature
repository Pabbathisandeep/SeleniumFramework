@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

	Background: 
	Given I landed on E commerce page
	
  @tag1
  Scenario Outline: Possitive test for submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to the cart
    And checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    
    Examples: 
    	|	name								|	password	|	productName	|
    	|	sandy1234@gmail.com	|	Virat@18	|	ZARA COAT 3	|
    	
   
