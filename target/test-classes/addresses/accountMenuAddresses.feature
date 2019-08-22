# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1043
@focus
Feature: [QA] Account: Menüpunkt Adressen
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Adressen" weitere Menü-Unterpunkte sehen,
  damit ich die verschiedenen Adresssparten genauer auswählen kann

  @ugly
  Scenario: Check menu entries addresses
    Given I have logged into my account as standard user
    When I look at the menu entry "navigationView.addresslist.Adressen"
    Then all the onmouse over entries looked for are being displayed
      | Person Alle        | navigationView.Adressen.Person Alle        |
      | Firma Alle         | navigationView.Adressen.Firma Alle         |
      | Person Neu         | navigationView.Adressen.Person Neu         |
      | Firma Neu          | navigationView.Adressen.Firma Neu          |
      | Profil importieren | navigationView.Adressen.Profil importieren |

  @ugly
  Scenario Outline: Check menu entries addresses goals
    Given I have logged into my account as standard user
    When I look at the menu entry "navigationView.addresslist.Adressen"
    And I click on the menu entry "<locator>"
    Then the target page is being displayed "<target page locator>"
    Examples:
      | Entry name         | locator                                    | target page locator  |
      | Person Alle        | navigationView.Adressen.Person Alle        | /addresslist/person  |
      | Firma Alle         | navigationView.Adressen.Firma Alle         | /addresslist/company |
      | Person Neu         | navigationView.Adressen.Person Neu         | /person/create       |
      | Firma Neu          | navigationView.Adressen.Firma Neu          | /company/create      |
      | Profil importieren | navigationView.Adressen.Profil importieren | /profile/create      |