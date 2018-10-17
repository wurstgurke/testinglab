# Akzeptanzkriterien siehe https://portal.neusta.de/jira/browse/BFY-1040
@focus
Feature: [QA] Login
  Als B4U-Nutzer möchte ich mich in meinen B4U-Account einloggen können, damit ich mit meinem B4U-Account arbeiten kann

  @ugly
  Scenario Outline: Ensure that all necessary login fields are displayed
    Given I am on the login page
    When I look for the element "<locator>"
    Then the element looked for is being displayed
    Examples:
      | field    | locator            |
      | Username | loginView.userName |
      | Password | loginView.password |
      | Anmelden | loginView.login    |
  @focus
  Scenario: Login success [standard user]
    Given I am on the login page
    And I provide the standard user credentials
    When I login
    Then the account page is being displayed

  Scenario: Login success [admin user]
    Given I am on the login page
    And I provide the admin user credentials
    When I login
    Then the account page is being displayed

  Scenario: Login failure wrong username [standard user]
    Given I am on the login page
    And I provide the standard user credentials
    And I provide an invalid username
    When I login
    Then an error message is being displayed "is-error"

  Scenario: Login failure wrong username [admin user]
    Given I am on the login page
    And I provide the admin user credentials
    And I provide an invalid username
    When I login
    Then an error message is being displayed "is-error"

  Scenario: Login failure wrong password [standard user]
    Given I am on the login page
    And I provide the standard user credentials
    And I provide an invalid password
    When I login
    Then an error message is being displayed "is-error"

  Scenario: Login failure wrong password [admin user]
    Given I am on the login page
    And I provide the admin user credentials
    And I provide an invalid password
    When I login
    Then an error message is being displayed "is-error"

  Scenario: Login failure wrong username wrong password
    Given I am on the login page
    And I provide an invalid username
    And I provide an invalid password
    When I login
    Then an error message is being displayed "is-error"