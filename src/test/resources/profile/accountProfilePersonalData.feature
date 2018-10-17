# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1105

Feature: [QA] Profil: Persönliche Daten
  Als B4U-Nutzer möchte ich in einem Profil in dem Bereich Persönliche Daten verschiedene Attributfelder sehen,
  damit ich das die persönlichen Daten differenzierter beschreiben kann

  @ugly
  Scenario: Check elements profile personal data
    Given I have logged into my account as standard user
    When I select the menu entry "topBannerView.loggedInPerson"
    And I click on the button "personDetailsFormView.edit"
    Then all the elements looked for are being displayed
      | Anrede                        | personDetailsFormView.salutationTypes                 |
      | Titel                         | personDetailsFormView.titles                          |
      | Vorname                       | personDetailsFormView.firstName                       |
      | Nachname                      | personDetailsFormView.lastName                        |
      | Position                      | personDetailsFormView.position                        |
      | Abteilung                     | personDetailsFormView.department                      |
      | Firma                         | personDetailsFormView.selectedCompany                 |
      #| Location Liste                | personDetailsFormView.locationList                    |
      | Neue Location hinzufügen      | personDetailsFormView.addWithCompany                  |
      | Neuer Email Adresstyp         | emailAdressFormView.emailAddress[0].emailAddressTypes |
      | Neue Email Adresse            | emailAdressFormView.emailAddress[0].address           |
      | Neue Email Adresse hinzufügen | newEmailAddressFormView.add                           |
      | Website URL                   | personDetailsFormView.url                             |
      | Geburtsdatum                  | personDetailsFormView.birthdate                       |
      | Nationalität                  | personDetailsFormView.nationality                     |
      | Hochladen                     | fileUploaderView.upload                               |
      | VCard export                  | personDetailsFormView.exportVCard                     |
      | Speichern                     | personDetailsFormView.update                          |

  @ugly
  Scenario: Check dropdown salutation types
    Given I have logged into my account as standard user
    When I select the menu entry "topBannerView.loggedInPerson"
    And I click on the button "personDetailsFormView.edit"
    Then the dropdown menu "personDetailsFormView.salutationTypes" contains the following items
      | Herr      |
      | Frau      |

  @ugly
  Scenario: Check dropdown titles
    Given I have logged into my account as standard user
    When I select the menu entry "topBannerView.loggedInPerson"
    And I click on the button "personDetailsFormView.edit"
    Then the dropdown menu "personDetailsFormView.titles" contains the following items
      | Ohne      |
      | Dr.       |
      | Prof. Dr. |

  @ugly
  Scenario: Check dropdown email address types
    Given I have logged into my account as standard user
    When I select the menu entry "topBannerView.loggedInPerson"
    And I click on the button "personDetailsFormView.edit"
    Then the dropdown menu "emailAdressFormView.emailAddress[0].emailAddressTypes" contains the following items
      | Geschäftlich |
      | Privat       |
      | Andere       |

  @ugly
  Scenario: Check dropdown phone types
    Given I have logged into my account as standard user
    When I select the menu entry "topBannerView.loggedInPerson"
    And I click on the button "personDetailsFormView.edit"
    Then the dropdown menu "phoneFormView.phone[0].phoneTypes" contains the following items
      | Geschäftlich |
      | Mobil        |
      | Privat       |
      | Fax          |
      | Andere       |
      | Homeoffice   |