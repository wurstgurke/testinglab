# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1051

Feature: [QA] Account: Menüpunkt Bewerbungen
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Bewerbungen" weitere Menü-Unterpunkte sehen,
  damit ich die verschiedenen E-Mail Sparten genauer auswählen kann

  @ugly
  Scenario: Check menu entries candidacies
    Given I have logged into my account as standard user
    When I look at the menu entry "navigationView.Bewerbungen"
    Then all the onmouse over entries looked for are being displayed
      | Alle | navigationView.Bewerbungen.Alle |

  @ugly
  Scenario Outline: Check menu entries candidacies goals
    Given I have logged into my account as standard user
    When I look at the menu entry "navigationView.Bewerbungen"
    And I click on the menu entry "<locator>"
    Then the target page is being displayed "<target page locator>"
    Examples:
      | Entry name | locator                         | target page locator |
      | Alle       | navigationView.Bewerbungen.Alle | /jobAccount         |