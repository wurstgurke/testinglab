Feature: [QA] Kontakte editieren
  Als B4U-Nutzer möchte ich bestehende Kontakte editieren,
  damit ich die hinterlegten Daten neuen Begebenheiten anpassen kann

  Scenario: Change the details of an existing contact
    Given I have logged into my account as standard user
    And I have checked that the contact "Justice League" does not already exist
    And I have checked that the contact "The Avengers" does not already exist
    And I create a new simple contact "Justice League"
    When I am on the contact edit page of "Justice League"
    And I edit the contact details
      | name          | The Avengers                         |
      | forperson     | Admin, TestB4U                       |
      | withperson    | Prüser, Thorsten                     |
    Then the contact details are
      | name          | The Avengers                         |
      | forperson     | Admin, TestB4U                       |
      | withperson    | Prüser, Thorsten                     |

  Scenario: Add a new note to an existing contact
    Given I have logged into my account as standard user
    And I have ensured that the contact "Justice League" already exist
    When I am on the contact edit page of "Justice League"
    And I add a new note to the contact "I am Batman!!!"
    And I add a new note to the contact "I am Groot!!!"
    Then the contact history should only contain
      |  TestB4U User | I am Groot!!!  |
      |  TestB4U User | I am Batman!!! |
    