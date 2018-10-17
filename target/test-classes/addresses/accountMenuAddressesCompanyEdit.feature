Feature: [QA] Adressen: Company editieren
  Als B4U-Nutzer möchte ich die Daten einer Firma ändern können,
  damit eine Firma mit den mir bekannten relevanten Merkmalen anpassen kann.

  Scenario: Change company details [without contact types]
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I have checked that the company "Stark Industries" does not already exist
    And I create a new company "Wayne Enterprise"
      | contacttype   | Interessent               |
    When I am on the edit company page of "Wayne Enterprise"
    And I edit the company details
      | name          | Stark Industries          |
      | website       | https://stark.industries  |
      | email         | tony@stark.industries     |
    Then the company details are
      | name          | Stark Industries          |
      | website       | https://stark.industries  |
      | email         | tony@stark.industries     |
      | contacttype   | Interessent               |


  Scenario: Change company location
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I have checked that the company "Stark Industries" does not already exist
    And I create a new company "Wayne Enterprise"
      | contacttype   | Interessent               |
    When I am on the edit company page of "Wayne Enterprise"
    And I edit the company location
      | loc.street    | Musterstraße              |
      | loc.zipcode   | 31337                     |
      | loc.city      | Gotham City               |
      | loc.country   | Deutschland               |
      | loc.phone     | Geschäftlich: 1234567890  |
    Then the company details are
      | name          | Wayne Enterprise                             |
      | loc.address   | Musterstraße, 31337 Gotham City, Deutschland |
      | loc.phone     | Geschäftlich 1234567890                      |
      | contacttype   | Interessent                                  |


  Scenario: Change company contact types
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I have checked that the company "Stark Industries" does not already exist
    And I create a new company "Wayne Enterprise"
      | contacttype   | Interessent               |
    When I am on the edit company page of "Wayne Enterprise"
    And I edit the company contact types
      | contacttype   | Kunde                     |
    Then the company details are
      | name          | Wayne Enterprise          |
      | contacttype   | Interessent               |
      | contacttype   | Kunde                     |


  Scenario: Add new contact to an existing company
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I have checked that the contact "Justice League" does not already exist
    And I create a new company "Wayne Enterprise"
      | contacttype   | Interessent               |
    When I am on the edit company page of "Wayne Enterprise"
    And I add a new contact "Justice League" to the company
    And I am on the edit company page of "Wayne Enterprise"
    Then the company contact list should only contain
      | | Justice League | | TestB4U User | * | * |


  Scenario: Add a skill to an existing company
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I create a new company "Wayne Enterprise"
      | contacttype   | Interessent               |
    When I am on the edit company page of "Wayne Enterprise"
    And I add a new skill "Geld, Geld, Geld" to the company
    Then the company has the skill "Geld, Geld, Geld" set


  Scenario: Remove a skill from an existing company
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I create a new company "Wayne Enterprise"
      | contacttype   | Interessent               |
    And I am on the edit company page of "Wayne Enterprise"
    And I add a new skill "Geld, Geld, Geld" to the company
    When I delete the skill "Geld, Geld, Geld" from the company
    Then the company has the skill "Geld, Geld, Geld" not set


  Scenario: Create a new person for an existing company
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I create a new company "Wayne Enterprise"
      | contacttype   | Interessent               |
    And I am on the edit company page of "Wayne Enterprise"
    When I create a new person "Bruce Wayne" for the company
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
      | name          | Herr Bruce Wayne                                |
      | position      | Batman                                          |
      | department    | Verbrechensbekämpfung                           |
      | phone         | Geschäftlich +49 123456789                      |
      | website       | https://wayne.enterprise/                       |
      | email         | Geschäftlich bruce@wayne.enterprise             |
      | birthdate     | 01.01.1980                                      |
      | address       | 🏢 Musterstraße, 31337 Gotham City, Deutschland |
      | contacttype   | Interessent                                     |
    And I am on the edit company page of "Wayne Enterprise"
    And the company employee list contains
      | Bruce Wayne   | Batman     | Verbrechensbekämpfung     |


  Scenario: Add an existing person to an existing company
    Given I have logged into my account as standard user
    And I have checked that the company "Wayne Enterprise" does not already exist
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I create a new company "Wayne Enterprise"
      | contacttype   | Interessent               |
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                      |
      | contacttype   | Interessent               |
    When I am on the edit company page of "Wayne Enterprise"
    And I add the person "Bruce Wayne" to the company
    Then the company employee list contains
      | Bruce Wayne   | | |