Feature: Blackjack assessment for Sands
    This is an assessment for the SDET position at Sands

  Scenario: Determine the outcome of 2 cards dealt to the player
    Given I am playing a game of blackjack
    When I am dealt 2 cards
    Then I must decide which action to take next