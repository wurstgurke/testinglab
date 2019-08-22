Feature: [QA] Adressen: Person editieren
  Als B4U-Nutzer möchte ich die Daten einer Person ändern können,
  damit eine Person mit den mir bekannten relevanten Merkmalen anpassen kann.

  Scenario: Change person details [without contact types]
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I have checked that the person "Kyle, Selina" does not already exist
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    When I am on the edit person page of "Bruce Wayne"
    And I edit the person details
      | salutation    | Frau                                    |
      | firstname     | Selina                                  |
      | lastname      | Kyle                                    |
      | position      | Catwomen                                |
      | department    | Verbrechensbekämpfung                   |
      | phone         | Geschäftlich: 9876543210                |
      | website       | https://wayne.enterprise                |
      | email         | Geschäftlich: selina@wayne.enterprise   |
      | birthdate     | 01.01.1985                              |
    Then the person details are
      | name          | Frau Selina Kyle                        |
      | position      | Catwomen                                |
      | department    | Verbrechensbekämpfung                   |
      | phone         | Geschäftlich 9876543210                 |
      | website       | https://wayne.enterprise/               |
      | email         | Geschäftlich selina@wayne.enterprise    |
      | birthdate     | 01.01.1985                              |
      | contacttype   | Interessent                             |


  Scenario: Change person address
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I have checked that the person "Kyle, Selina" does not already exist
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    When I am on the edit person page of "Bruce Wayne"
    And I edit the person address
      | street        | Musterstraße                            |
      | zipcode       | 31337                                   |
      | city          | Gotham City                             |
      | country       | Deutschland                             |
      | addresstype   | Geschäftlich                            |
    Then the person details are
      | name          | Herr Bruce Wayne                             |
      | address       | 🏢 Musterstraße, 31337 Gotham City, Deutschland |
      | contacttype   | Interessent                                  |


  Scenario: Change person contact types
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I have checked that the person "Kyle, Selina" does not already exist
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    When I am on the edit person page of "Bruce Wayne"
    And I edit the person contact types
      | contacttype   | Kunde                                   |
    Then the person details are
      | name          | Herr Bruce Wayne          |
      | contacttype   | Interessent               |
      | contacttype   | Kunde                     |


    Scenario: Add new contact to an existing person
      Given I have logged into my account as standard user
      And I have checked that the person "Wayne, Bruce" does not already exist
      And I have checked that the contact "Justice League" does not already exist
      And I create a new person "Bruce Wayne"
        | salutation    | Herr                                    |
        | contacttype   | Interessent                             |
      When I am on the edit person page of "Bruce Wayne"
      And I add a new contact "Justice League" to the person
      And I am on the edit person page of "Bruce Wayne"
      Then the person contact list should only contain
        | | Justice League | Bruce Wayne | TestB4U User | * | * |


  Scenario: Add a know how to an existing person
      Given I have logged into my account as standard user
      And I have checked that the person "Wayne, Bruce" does not already exist
      And I create a new person "Bruce Wayne"
        | salutation    | Herr                                    |
        | contacttype   | Interessent                             |
      When I am on the edit person page of "Bruce Wayne"
      And I add a new know how "Ninjutsu"
      Then the person has the know how "Ninjutsu" set


  Scenario: Remove a know how from an existing person
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I am on the edit person page of "Bruce Wayne"
    And I add a new know how "Ninjutsu"
    When I delete the know how "Ninjutsu" from the person
    Then the person has the know how "Ninjutsu" not set


  Scenario: Add a skill to an existing person
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I am on the create new person page
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    When I am on the edit person page of "Bruce Wayne"
    And I add a new skill "Geld, Geld, Geld" to the person
    Then the person has the skill "Geld, Geld, Geld" set


  Scenario: Remove a skill from an existing person
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I am on the create new person page
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I am on the edit person page of "Bruce Wayne"
    And I add a new skill "Geld, Geld, Geld" to the person
    When I delete the skill "Geld, Geld, Geld" from the person
    Then the person has the skill "Geld, Geld, Geld" not set


  Scenario: Add a new project to an existing person
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    When I am on the edit person page of "Bruce Wayne"
    And I add a new project
      | ordernumber   | 1                          |
      | startdate     | 01.01.2017                 |
      | enddate       | 31.12.2017                 |
      | position      | Team-Lead                  |
      | customertrade | Justice League             |
      | description   | I am Batman!               |
      | technical     | Because I am Batman!       |
    And I am on the edit person page of "Bruce Wayne"
    Then the person project list should only contain
      | 1 | * | 01.01.2017 - 31.12.2017 | Justice League | Team-Lead | I am Batman! |


  Scenario: Remove a project from an existing person
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    And I am on the edit person page of "Bruce Wayne"
    And I add a new project
      | ordernumber   | 1                          |
      | startdate     | 01.01.2017                 |
      | enddate       | 31.12.2017                 |
      | position      | Team-Lead                  |
      | customertrade | Justice League             |
      | description   | I am Batman!               |
      | technical     | Because I am Batman!       |
    And I am on the edit person page of "Bruce Wayne"
    When I delete the project "1"
    Then the person project list should be empty


  Scenario: Edit person freelancer data
    Given I have logged into my account as standard user
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Freiberufler/in                         |
    When I am on the edit person page of "Bruce Wayne"
    And I edit the person freelancer details
      | cooperationsince | 01.01.2016 |
      | availablesince   | 01.01.2017 |
      | freelancerstate  | neu        |
      | hourlyratemin    | 10 EUR     |
      | hourlyratemax    | 20 EUR     |
      | contracttype     | Contract   |
      | rating           | **         |
    Then the person freelancer details are
      | cooperationsince | 01.01.2016 |
      | availablesince   | 01.01.2017 |
      | freelancerstate  | neu        |
      | hourlyratemin    | 10 EUR     |
      | hourlyratemax    | 20 EUR     |
      | contracttype     | Contract   |
      | rating           | **         |

