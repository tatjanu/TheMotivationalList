Feature: Tasks

  The User should be able to create a Task List for his motivation list

  Scenario: Bob wants to create a task
    Given Bob is in the app
    And has an Motivation list
    When Bob creates a taks
    Then he should see it in the recycle view todo list
    