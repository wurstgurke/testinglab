# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1047

Feature: [QA] Account: Menüpunkt E-Mail
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "E-Mail" weitere Menü-Unterpunkte sehen,
  damit ich die verschiedenen E-Mail Sparten genauer auswählen kann

  @ugly
  Scenario: Check menu entries email
    Given I have logged into my account as standard user
    When I look at the menu entry "navigationView.E-Mail"
    Then all the onmouse over entries looked for are being displayed
      | Alle | navigationView.E-Mail.Alle |
      | Neu  | navigationView.E-Mail.Neu  |

  @ugly
  Scenario Outline: Check menu entries email goals
    Given I have logged into my account as standard user
    When I look at the menu entry "navigationView.E-Mail"
    And I click on the menu entry "<locator>"
    Then the target page is being displayed "<target page locator>"
    Examples:
      | Entry name | locator                    | target page locator |
      | Alle       | navigationView.E-Mail.Alle | /email              |
      | Neu        | navigationView.E-Mail.Neu  | /email/create       |