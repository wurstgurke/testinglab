# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1062

Feature: [QA] Kontakte: Neu
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Kontakte" eine Maske mit Eingabemöglichkeiten
  für verschiedene kontaktbezogene Merkmale sehen,
  damit einen neuen Kontakt anlegen kann

  @ugly
  Scenario: Check elements addresses menu person new
    Given I have logged into my account as standard user
    When I am on the create new contact page
    Then all the elements looked for are being displayed
      | Kontakt        | contactView.name        |
      | Kontakt für    | contactView.forPerson   |
      | Kontakt mit    | contactView.withPerson  |
      | Kontaktfirma   | contactView.withCompany |
      | Speichern      | contactView.save        |
      | Sortieren nach | contactView.down        |

  Scenario: Contact new create success
    Given I have logged into my account as standard user
    And I have checked that the contact "Justice League" does not already exist
    When I create a new contact "Justice League"
      | forperson     | User, TestB4U                       |
      | withperson    | Prüser, Thorsten                    |
    Then the contact "Justice League" is created
    And I am on the contact edit page of "Justice League"
    And the contact details are
      | name          | Justice League                       |
      | forperson     | User, TestB4U                        |
      | withperson    | Prüser, Thorsten                     |
      | withcompany   | neusta consulting GmbH               |

  Scenario: Contact new create success [minimal requirements]
    Given I have logged into my account as standard user
    And I have checked that the contact "Justice League" does not already exist
    When I create a new simple contact "Justice League"
    Then the contact "Justice League" is created
    And I am on the contact edit page of "Justice League"
    And the contact details are
      | name          | Justice League                        |
      | forperson     | User, TestB4U                         |
      | withperson    | User, TestB4U                         |
      | withcompany   |                                       |

  Scenario: Contact new create failure
    Given I have logged into my account as standard user
    And I have checked that the contact "Justice League" does not already exist
    When I create a new contact ""
      | forperson     | User, TestB4U                         |
      | withperson    | Admin, TestB4U                        |
    Then an error message is being displayed "required"