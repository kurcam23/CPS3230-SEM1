Feature: Search functionality

  I want to be able to search for stuff


  Scenario: Simple Search
    Given I am using Scan Malta website
    When I search for "Laptop"
    Then the title should be "SCAN | Your Trusted Choice - Search results for: 'Laptop'"