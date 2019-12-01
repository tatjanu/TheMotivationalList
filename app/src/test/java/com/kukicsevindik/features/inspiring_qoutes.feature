#created by Mert 01.12
Feature: inspiring quotes

  The User should see an inspiring quote at the bottom of his list

  Scenario: Bob wants to see a quote

    Given Bob starts app
    When Bob is on a list or creates a list
    Then he should see at the bottom of the screen an inspiring quote



