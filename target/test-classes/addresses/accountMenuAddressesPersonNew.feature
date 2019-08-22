# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1055
Feature: [QA] Adressen: Person Neu
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Person Neu" eine Anlagemaske mit Eingabemöglichkeiten
  für verschiedene personenbezogene Merkmale sehen,
  damit eine neue Person mit den mir bekannten relevanten Merkmalen im System anlegen kann

  @ugly
  Scenario: Check elements addresses menu person new
    Given I have logged into my account as standard user
    When I am on the create new person page
    Then all the elements looked for are being displayed
      | Vorname                    | personDetailsFormView.firstName                       |
      | Nachname                   | personDetailsFormView.lastName                        |
      | Position                   | personDetailsFormView.position                        |
      | Abteilung                  | personDetailsFormView.department                      |
      | Firma                      | personDetailsFormView.selectedCompany                 |
      | Art der E-Mail Adresse     | emailAdressFormView.emailAddress[0].emailAddressTypes |
      | Email-Adresse              | emailAdressFormView.emailAddress[0].address           |
      | Nationalität               | personDetailsFormView.nationality                     |
      | Strasse                    | addressFormView.address[0].street                     |
      | PLZ                        | addressFormView.address[0].zipCode                    |
      | Stadt                      | addressFormView.address[0].city                       |
      | Land                       | addressFormView.address[0].countries                  |
      | Anrede                     | personDetailsFormView.salutationTypes                 |
      | Titel                      | personDetailsFormView.titles                          |
      | VCard Import               | personCreateFormView.parseVCard                       |
      | Speichern 1                | personDetailsFormView.create                          |
      | Speichern 2                | person.createPerson                                   |
      | Kunde                      | contactTypeFormView.contactTypes[0].CUSTOMER        |
      | Freiberufler/in            | contactTypeFormView.contactTypes[1].FREELANCER      |
      | Partner                    | contactTypeFormView.contactTypes[2].PARTNER         |
      | Lieferant                  | contactTypeFormView.contactTypes[3].SUPPLIER        |
      | Mitarbeiter/in             | contactTypeFormView.contactTypes[4].EMPLOYEE        |
      | Angestellte/r (extern)     | contactTypeFormView.contactTypes[5].EMPLOYEE_EXTERN |
      | Interessent                | contactTypeFormView.contactTypes[6].INTERESTED      |
      | team neusta                | contactTypeFormView.contactTypes[7].TEAM_NEUSTA     |
      | Ein besonderer Tag         | campaignsFormView.campaign.0                          |
      | Weihnachtsgeschenk         | campaignsFormView.campaign.1                          |
      | Weihnachtsgruß             | campaignsFormView.campaign.2                          |
      | Willkommensbox erhalten    | campaignsFormView.campaign.3                          |

  Scenario: Person new create success
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    When I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | position      | Batman                                  |
      | department    | Verbrechensbekämpfung                   |
      | phone         | Geschäftlich: 0123456789                |
      | website       | https://wayne.enterprise                |
      | email         | Geschäftlich: bruce@wayne.enterprise    |
      | birthdate     | 01.01.1980                              |
      | street        | Musterstraße                            |
      | zipcode       | 31337                                   |
      | city          | Gotham City                             |
      | country       | Deutschland                             |
      | contacttype   | Interessent                             |
    Then the person "Bruce Wayne" is created
    And I am on the edit person page of "Bruce Wayne"
    And the person details are
      | name          | Herr Bruce Wayne                             |
      | position      | Batman                                       |
      | department    | Verbrechensbekämpfung                        |
      | phone         | Geschäftlich +49 123456789                   |
      | website       | https://wayne.enterprise/                    |
      | email         | Geschäftlich bruce@wayne.enterprise          |
      | birthdate     | 01.01.1980                                   |
      | address       | 🏢 Musterstraße, 31337 Gotham City, Deutschland |
      | contacttype   | Interessent                                  |

  Scenario: Person new create success [minimal requirements]
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    When I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    Then the person "Bruce Wayne" is created
    And I am on the edit person page of "Bruce Wayne"
    And the person details are
      | name          | Herr Bruce Wayne                        |
      | contacttype   | Interessent                             |

  Scenario: Person new create error [missing contact type]
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    When I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
    Then an error message is being displayed "required"

  Scenario: Person new create success
    Given I have logged into my account as standard test12