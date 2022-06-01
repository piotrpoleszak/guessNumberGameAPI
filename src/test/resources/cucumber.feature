Feature: Create new game

  Scenario: As a player I want to create a new game
    Given I can create a new game
    And I sending game to be created
    Then I should be able to see my newly game