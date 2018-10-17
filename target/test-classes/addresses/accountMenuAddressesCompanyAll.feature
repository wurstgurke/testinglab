# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1053

Feature: [QA] Adressen: Firma Alle
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Firma Alle" eine Suchmaske mit verschiedenen Suchattributen sehen,
  damit ich die die gesuchte Firma schneller finden kann

  @ugly
  Scenario: Check elements addresses menu company all
    Given I have logged into my account as standard user
    When I am on the company address list page
    Then all the elements looked for are being displayed
      | Name                | companyAddressTableView.nameOrId           |
      | Tel.Nr.             | companyAddressTableView.phone              |
      | PLZ                 | companyAddressTableView.zipCode            |
      | Stadt               | companyAddressTableView.city               |
      | Straße              | companyAddressTableView.street             |
      | Kontakttyp          | companyAddressTableView.contactTypes       |
      | Kernkompetenzen     | companyAddressTableView.skill              |
      | Suchen 1            | companyAddressTableView.search1            |
      | Suchen 2            | companyAddressTableView.search2            |
      | Neu anlegen         | companyAddressTableView.create             |
      | Filter zurücksetzen | companyAddressTableView.search.clearFilter |

  @ugly
  Scenario: Check dropdown entries addresses menu company all
    Given I have logged into my account as standard user
    When I am on the company address list page
    Then the dropdown menu "companyAddressTableView.contactTypes" contains the following items
      | - alle -        |
      | Kunde           |
      | Freiberufler/in |
      | Partner         |
      | Lieferant       |
      | Interessent     |
      | Kontakt         |
      | team neusta     |

  Scenario: Check if it is possible to navigate the create company page
    Given I have logged into my account as standard user
    And I am on the company address list page
    When I want to create a new company
    Then the create company page is being displayed

  Scenario: Check if it is possible to clear the search filter
    Given I have logged into my account as standard user
    And I am on the company address list page
    And I provide the following data to the company search filter
      | nameorid  | Mustercorp                 |
      | phone     | 012345678                  |
      | zipcode   | 31337                      |
      | city      | Musterstadt                |
      | street    | Musterstraße               |
      | skill     | Musterskill                |
    When I clear the company search filter
    Then the company search filter is empty

  Scenario: Check if the search results match the search filter criteria
    Given I have logged into my account as standard user
    And I am on the company address list page
    And I provide the following data to the company search filter
      | nameorid  | Fr. Lürssen Werft GmbH & Co. KG |
      | phone     | +49 421-6604-0                  |
      | zipcode   | 28759                           |
      | city      | Bremen                          |
      | street    | Zum alten Speicher 11           |
      | forperson | Thorsten Prüser                 |
    When I list the company search results
    Then the company search results should only contain
      | (13182) | Fr. Lürssen Werft GmbH & Co. KG | Lürssen-Gruppe | Zum alten Speicher 11 | 28759 | Bremen | 🏢 +49 421-6604-0 | Thorsten Prüser |

  Scenario: Check if the search filters are correct
    Given I have logged into my account as standard user
    And I am on the company address list page
    And I provide the following data to the company search filter
      | nameorid | Hammer Industries |
    When I list the company search results
    Then the company search results are empty

