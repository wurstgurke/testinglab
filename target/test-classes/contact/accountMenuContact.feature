# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1044

Feature: [QA] Account: Menüpunkt Kontakte
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Kontakte" weitere Menü-Unterpunkte sehen,
  damit ich die verschiedenen Kontaktsparten genauer auswählen kann

  @ugly
  Scenario: Check menu entries contact
    Given I have logged into my account as standard user
    When I look at the menu entry "navigationView.Kontakte"
    Then all the onmouse over entries looked for are being displayed
      | Alle | navigationView.Kontakte.Alle |
      | Neu  | navigationView.Kontakte.Neu  |

  @ugly
  Scenario Outline: Check menu entries contact goals
    Given I have logged into my account as standard user
    When I look at the menu entry "navigationView.Kontakte"
    And I click on the menu entry "<locator>"
    Then the target page is being displayed "<target page locator>"
    Examples:
      | Entry name | locator                      | target page locator |
      | Alle       | navigationView.Kontakte.Alle | /contact/list       |
      | Neu        | navigationView.Kontakte.Neu  | /contact/create     |