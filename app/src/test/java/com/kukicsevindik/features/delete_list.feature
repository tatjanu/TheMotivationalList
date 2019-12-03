Feature: Delete list

Scenario: Bob wants to delete a list
Given Bob is a user of ower app
And has created a List
When Bob clicks on the <option>
Then the list will be deleted

| option |
| swipe |
| trash icon|

