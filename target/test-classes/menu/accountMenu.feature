# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1041

Feature: [QA] Account: Menüzeile
  Als B4U-Nutzer möchte ich in meinem B4U-Account eine Menüzeile sehen, damit ich einfacher navigieren kann

  @ugly
  Scenario: Check menu entries that are visible as standard user
    Given I have logged into my account as standard user
    When I look at the menu entries
    Then all the menu entries looked for are being displayed
      | Dashboard       | navigationView.Dashboard            |
      | Adressen        | navigationView.addresslist.Adressen |
      | Kontakte        | navigationView.Kontakte             |
      | E-Mail          | navigationView.E-Mail               |
      | Bewerbungen     | navigationView.Bewerbungen          |
      | Aufgaben        | navigationView.Aufgaben             |
      | Ausschreibungen | navigationView.Ausschreibungen      |
#      | LogOn           | navigationView.LogOn                |
      | TestB4U User    | topBannerView.loggedInPerson        |

  @ugly
  Scenario: Check menu entries that are not visible as standard user
    Given I have logged into my account as standard user
    When I look at the menu entries
    Then all the menu entries looked for are not being displayed
      | Administration  | navigationView.Administration       |

  @ugly
  Scenario: Check menu entries that are visible as administrator
    Given I have logged into my account as admin user
    When I look at the menu entries
    Then all the menu entries looked for are being displayed
      | Dashboard       | navigationView.Dashboard            |
      | Adressen        | navigationView.addresslist.Adressen |
      | Kontakte        | navigationView.Kontakte             |
      | Administration  | navigationView.Administration       |
      | E-Mail          | navigationView.E-Mail               |
      | Bewerbungen     | navigationView.Bewerbungen          |
      | Aufgaben        | navigationView.Aufgaben             |
      | Ausschreibungen | navigationView.Ausschreibungen      |
#      | LogOn           | navigationView.LogOn                |
      | TestB4U Admin   | topBannerView.loggedInPerson        |