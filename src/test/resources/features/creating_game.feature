Feature: Create new game

  Scenario: As player I want to create new game
    Given I Set POST game service api endpoint
    When I create new game
    Then New game saved in database and return id