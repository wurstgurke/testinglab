# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1045

Feature: [QA] Account: Menüpunkt Administration
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Administration" weitere Menü-Unterpunkte sehen,
  damit ich die verschiedenen Administrationssparten genauer auswählen kann

  @ugly
  Scenario: Check menu entries administration
    Given I have logged into my account as admin user
    When I look at the menu entry "navigationView.Administration"
    Then all the onmouse over entries looked for are being displayed
      | Know-How      | navigationView.Administration.Know-How      |
      | Aufgabentypen | navigationView.Administration.Aufgabentypen |
      | Konfiguration | navigationView.Administration.Konfiguration |

  @ugly
  Scenario Outline: Check menu entries administration goals
    Given I have logged into my account as admin user
    When I look at the menu entry "navigationView.Administration"
    And I click on the menu entry "<locator>"
    Then the target page is being displayed "<target page locator>"
    Examples:
      | Entry name    | locator                                     | target page locator     |
      | Know-How      | navigationView.Administration.Know-How      | /knowhow/administration |
      | Aufgabentyp   | navigationView.Administration.Aufgabentypen | /taskType/list          |
      | Konfiguration | navigationView.Administration.Konfiguration | /configuration/list     |
