# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1056

Feature: [QA] Adressen: Company Neu
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Firma Neu" eine Anlagemaske Eingabemöglichkeiten für verschiedene
  firmenbezogene Merkmale sehen,
  damit eine neue Firma mit den mir bekannten relevanten Merkmalen im System anlegen kann

  @ugly
  Scenario: Check elements addresses menu company new
    Given I have logged into my account as standard user
    When I am on the create new company page
    Then all the elements looked for are being displayed
      | Name                                            | companyDetailsView.name                        |
      | Konzern                                         | companyDetailsView.corporation                 |
      | Website URL                                     | companyDetailsView.url                         |
      | Email                                           | companyDetailsView.email                       |
      | Strasse                                         | locationsView.location.0.street                |
      | PLZ                                             | locationsView.location.0.zipCode               |
      | Stadt                                           | locationsView.location.0.city                  |
      | Telefonnummer                                   | valueFormView.value[0].edit                    |
      | Adresse zum automatischen einlesen hier ablegen | companyDetailsView.addressParser               |
      | verantwortlich                                  | companyDetailsView.selectedKeyAccountManager   |
      | Land                                            | companyLocationslocation.0.countries           |
      | Art des Telefonanschlusses                      | valueFormView.value[0].types                   |
      | Telefonnummer hinzufügen                        | valueFormView.add                              |
      | Standort hinzufügen                             | locations.add                                  |
      | Speichern                                       | companyDetailsView.saveCreate                  |
      | Kunde                                           | contactTypeFormView.contactTypes[0].CUSTOMER    |
      | Freiberufler/in                                 | contactTypeFormView.contactTypes[1].FREELANCER  |
      | Partner                                         | contactTypeFormView.contactTypes[2].PARTNER     |
      | Lieferant                                       | contactTypeFormView.contactTypes[3].SUPPLIER    |
      | Interessent                                     | contactTypeFormView.contactTypes[4].INTERESTED  |
      | team neusta                                     | contactTypeFormView.contactTypes[5].TEAM_NEUSTA |

  Scenario: Company new create success
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I create a new company "Wayne Enterprise"
      | website       | https://wayne.enterprises |
      | email         | bruce@wayne.enterprise    |
      | loc.street    | Musterstraße              |
      | loc.zipcode   | 31337                     |
      | loc.city      | Gotham City               |
      | loc.country   | Deutschland               |
      | loc.phone     | Geschäftlich: 1234567890  |
      | contacttype   | Interessent               |
    Then the company "Wayne Enterprise" is created
    And I am on the edit company page of "Wayne Enterprise"
    And the company details are
      | name          | Wayne Enterprise                             |
      | website       | https://wayne.enterprises                    |
      | email         | bruce@wayne.enterprise                       |
      | loc.address   | Musterstraße, 31337 Gotham City, Deutschland |
      | loc.phone     | Geschäftlich 1234567890                      |
      | contacttype   | Interessent                                  |

  Scenario: Company new create success [minimal requirements]
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I create a new company "Wayne Enterprise"
      | contacttype   | Interessent               |
    Then the company "Wayne Enterprise" is created
    And I am on the edit company page of "Wayne Enterprise"
    And the company details are
      | name          | Wayne Enterprise          |
      | contacttype   | Interessent               |

  Scenario: Company new create failure [missing contact type]
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I create a new simple company "Wayne Enterprise"
    Then an error message is being displayed "required"