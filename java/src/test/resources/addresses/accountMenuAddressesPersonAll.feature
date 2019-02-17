# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1052
Feature: [QA] Adressen: Person Alle
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Person Alle" eine Suchmaske mit verschiedenen Suchattributen sehen,
  damit ich die gesuchte Person schneller finden kann

  @ugly
  Scenario: Check elements addresses menu person all
    Given I have logged into my account as standard user
    When I am on the person address list page
    Then all the elements looked for are being displayed
      | Name Or Id          | personAddressTableView.nameOrId           |
      | Tel.Nr.             | personAddressTableView.phone              |
      | E-Mail              | personAddressTableView.email              |
      | Firma               | personAddressTableView.company            |
      | PLZ                 | personAddressTableView.zipCode            |
      | Stadt               | personAddressTableView.city               |
      | Straße              | personAddressTableView.street             |
      | Kontakttyp          | personAddressTableView.contactTypes       |
      | Suchen 1            | personAddressTableView.search1            |
      | Suchen 2            | personAddressTableView.search2            |
      | Neu anlegen         | personAddressTableView.create             |
      | Filter zurücksetzen | personAddressTableView.search.clearFilter |

  @ugly
  Scenario: Check dropdown entries addresses menu person all
    Given I have logged into my account as standard user
    And I am on the person address list page
    Then the dropdown menu "personAddressTableView.contactTypes" contains the following items
      | - alle -               |
      | Kunde                  |
      | Freiberufler/in        |
      | Partner                |
      | Lieferant              |
      | Mitarbeiter/in         |
      | Angestellte/r (extern) |
      | Interessent            |
      | Kontakt                |
      | team neusta            |

  Scenario: Check if it is possible to navigate the create person page
    Given I have logged into my account as standard user
    And I am on the person address list page
    When I want to create a new person
    Then the create person page is being displayed

  Scenario: Check if it is possible to clear the search filter
    Given I have logged into my account as standard user
    And I am on the person address list page
    And I provide the following data to the person search filter
      | nameorid  | Max Mustermann             |
      | phone     | 012345678                  |
      | email     | max.mustermann@example.org |
      | company   | Mustercorp                 |
      | zipcode   | 31337                      |
      | city      | Musterstadt                |
      | street    | Musterstraße               |
    When I clear the person search filter
    Then the person search filter is empty

  Scenario: Check if the search results match the search filter criteria [Full]
    Given I have logged into my account as standard user
    And I am on the person address list page
    And I provide the following data to the person search filter
      | nameorid  | Thorsten Prüser        |
      | phone     | +49 40 3999903-210     |
      | email     | t.prueser@neusta.de    |
      | company   | neusta consulting GmbH |
      | zipcode   | 28357                  |
      | city      | Bremen                 |
      | street    | Butlandskamp 11 D      |
    When I list the person search results
    Then the person search results should only contain
      | (2037) | Prüser, Thorsten | neusta consulting GmbH | Butlandskamp 11 D | 28357 | Bremen | 🏢 +49 40 3999903-210 |

  Scenario: Check if the search results match the search filter criteria [LastName, FirstName]
    Given I have logged into my account as standard user
    And I am on the person address list page
    And I provide the following data to the person search filter
      | nameorid  | Prüser, Thorsten       |
    When I list the person search results
    Then the person search results should only contain
      | * | Prüser, Thorsten | * | * | * | * | * |

  Scenario: Check if the search results match the search filter criteria [FirstName]
    Given I have logged into my account as standard user
    And I have checked that the person "Banner, Brucie" does not already exist
    And I have checked that the person "Wayne, Brucie" does not already exist
    And I have checked that the person "Brucie, Banner" does not already exist
    And I create a new person "Brucie Banner"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I create a new person "Brucie Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I create a new person "Banner Brucie"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I am on the person address list page
    And I provide the following data to the person search filter
      | nameorid  | ,Brucie              |
    When I list the person search results
    Then the person search results should only contain
      | * | Banner, Brucie | * | * | * | * | * |
      | * | Wayne, Brucie  | * | * | * | * | * |

  Scenario: Check if the search results match the search filter criteria [LastName]
    Given I have logged into my account as standard user
    And I have checked that the person "Skywalker, Luke" does not already exist
    And I have checked that the person "Skywalker, Anakin" does not already exist
    And I have checked that the person "Luke, Skywalker" does not already exist
    And I create a new person "Luke Skywalker"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I create a new person "Anakin Skywalker"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I create a new person "Skywalker Luke"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I am on the person address list page
    And I provide the following data to the person search filter
      | nameorid  | Skywalker,              |
    When I list the person search results
    Then the person search results should only contain
      | * | Skywalker, Anakin | * | * | * | * | * |
      | * | Skywalker, Luke   | * | * | * | * | * |

  Scenario: Check if the search results match the search filter criteria [All]
    Given I have logged into my account as standard user
    And I have checked that the person "Skywalker, Luke" does not already exist
    And I have checked that the person "Lukewalker, Sky" does not already exist
    And I have checked that the person "Luke, Skywalker" does not already exist
    And I create a new person "Luke Skywalker"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I create a new person "Sky Lukewalker"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I am on the person address list page
    And I provide the following data to the person search filter
      | nameorid  | Luke              |
    When I list the person search results
    Then the person search results should only contain
      | * | Lukewalker, Sky | * | * | * | * | * |
      | * | Skywalker, Luke | * | * | * | * | * |

  Scenario: Check if the search filters are correct
    Given I have logged into my account as standard user
    And I am on the person address list page
    And I provide the following data to the person search filter
      | nameorid  | Tony Stark              |
    When I list the person search results
    Then the person search results are empty

  Scenario: Check if the keyword filter is correct [Knowhow]
    Given I have logged into my account as standard user
    And I have checked that the person "Banner, Bruce" does not already exist
    And I create a new person "Bruce Banner"
      | salutation    | Herr                                    |
      | contacttype   | Freiberufler/in                         |
    And I add a new know how "Gamma-Strahlen"
    And I am on the person address list page
    And I provide the following data to the person search filter
      | contacttype   | Freiberufler/in                         |
      | keywords      | Gamma-Strahlen                          |
    When I list the person search results
    Then the freelancer search results should only contain "Banner, Bruce"

  Scenario: Check if the keyword filter is correct [Skill]
    Given I have logged into my account as standard user
    And I have checked that the person "Banner, Bruce" does not already exist
    And I create a new person "Bruce Banner"
      | salutation    | Herr                                    |
      | contacttype   | Freiberufler/in                         |
    And I add a new skill "Wutbewältigung" to the person
    And I am on the person address list page
    And I provide the following data to the person search filter
      | contacttype   | Freiberufler/in                         |
      | keywords      | Wutbewältigung                          |
    When I list the person search results
    Then the freelancer search results should only contain "Banner, Bruce"

  Scenario: Check if the keyword filter is correct [Projects]
    Given I have logged into my account as standard user
    And I have checked that the person "Banner, Bruce" does not already exist
    And I create a new person "Bruce Banner"
      | salutation    | Herr                                    |
      | contacttype   | Freiberufler/in                         |
    And I add a new project
      | ordernumber   | 1                          |
      | startdate     | 01.01.2017                 |
      | enddate       | 31.12.2017                 |
      | position      | HULK                       |
      | customertrade | Justice League             |
      | description   | HUULK...                   |
      | technical     | ...SMASH!!                 |
    And I am on the person address list page
    And I provide the following data to the person search filter
      | contacttype   | Freiberufler/in                         |
      | keywords      | HUULK                                   |
    When I list the person search results
    Then the freelancer search results should only contain "Banner, Bruce"