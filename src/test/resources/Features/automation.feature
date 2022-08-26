@test
Feature: Feature to test login functionality

@test1
Scenario: Check user is able to create new article successfully

Given user launches the URL
When user is navigated to the home page
And user sign in with credentials
And user navigates to new article page
And user gives article details
Then article should be created

@test2
Scenario: Check user is able to edit the created article successfully

Given user launches the URL
When user is navigated to the home page
And user sign in with credentials
And user navigates to user page
And user edits the article details
Then edited article details should be updated

@test3
Scenario: Check user is able to see the article in Global feed

Given user launches the URL
When user is navigated to the home page
And user sign in with credentials
And user navigates to Global feed
Then user should see the created article

@test4
Scenario: Check user is able to add & delete the comment to artice

Given user launches the URL
When user is navigated to the home page
And user sign in with credentials
And user navigates to user page
And user adds the comment and posted to the article
Then user should see the added the comment

@test5
Scenario: Check user is able to delete the comment from article

Given user launches the URL
When user is navigated to the home page
And user sign in with credentials
And user navigates to user page
And user adds the comment and posted to the article
And user deletes the added comment
Then comment should be deleted

@test6
Scenario: Check user is able to delete the artice

Given user launches the URL
When user is navigated to the home page
And user sign in with credentials
And user navigates to user page
And user deletes the created article
Then article should be deleted








