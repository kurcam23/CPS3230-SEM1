Feature: Search functionality

  I want to be able to search for stuff

  Scenario: Valid Login
    Given I am a user on the website
    When I log in using valid credentials
    Then I should be logged in

  Scenario: Invalid Login
    Given I am a user on the website
    When I log in using invalid credentials
    Then I should not be logged in

  Scenario: Product Search
    Given I am a logged in user on the website
    When I search for a product
    And I select the first product in the list
    Then I should see the product details

  Scenario: Add product to cart
    Given I am a logged in user on the website
    And my shopping cart is empty
    When I view the details of a product
    And I choose to buy the product
    Then my shopping cart should contain 1 item

  Scenario: Add multiple products to cart
    Given I am a logged in user on the website
    And my shopping cart is empty
    When I add 3 products to my shopping cart
    Then my shopping cart should contain 3 item

  Scenario: Add multiple products to cart
    Given I am a logged in user on the website
    And my shopping cart is empty
    When I add 5 products to my shopping cart
    Then my shopping cart should contain 5 item

  Scenario: Add multiple products to cart
    Given I am a logged in user on the website
    And my shopping cart is empty
    When I add 9 products to my shopping cart
    Then my shopping cart should contain 9 item

#  Scenario: Removing a product from cart
#    Given I am a logged in user on the website
#    And my shopping cart has 2 products
#    When I remove the first product in my cart
#    Then my shopping cart should contain 1 item