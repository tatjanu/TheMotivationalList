Feature: create a list
  The User should be able to create a motivation list

  Scenario: Bob wants to create motivation list
    Given Bob is in the app
    When Bob clicks on create
    And enters a name for his list
    And submits it
    Then Bob should see his list in the recycle View


