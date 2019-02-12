# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1042
Feature: [QA] Logout
  Als B4U-Nutzer möchte ich mich aus meinem B4U-Account wieder ausloggen können,
  damit mein Account geschützt ist, wenn ich nicht mehr damit arbeite

  @ugly
  Scenario Outline: Ensure that the logout button is displayed
    Given I have logged into my account as standard user
    When I look for the element "<locator>"
    Then the element looked for is being displayed
    Examples:
      | field    | locator              |
      | Username | topBannerView.logout |

  Scenario: logout success [standard user]
    Given I have logged into my account as standard user
    When I logout
    Then the login page is being displayed

  Scenario: logout success [admin user]
    Given I have logged into my account as admin user
    When I logout
    Then the login page is being displayed