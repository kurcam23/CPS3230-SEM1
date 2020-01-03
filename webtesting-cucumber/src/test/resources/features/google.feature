Feature: Search functionality

  I want to be able to search for stuff


  Scenario: Simple Search
    Given I am using the Google website
    When I search for "Malta"
    Then the title should be "Malta - Google Search"