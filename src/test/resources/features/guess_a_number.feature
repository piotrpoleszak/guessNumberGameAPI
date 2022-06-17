Feature: Guess a number

  Scenario: As player I want to guess a number
    Given I Set PUT game service api endpoint
    When I guess a number
    Then Message is returned "NUMBER_GUESSED"