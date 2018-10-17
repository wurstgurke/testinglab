# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1057

Feature: [QA] Kontakte: Alle
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Kontakte" eine Suchmaske mit Eingabemöglichkeiten
  für verschiedene kontaktbezogenen Merkmalen sehen,
  damit ich den gesuchten Kontakt schneller finden kann

  @ugly
  Scenario: Check elements contact menu all
    Given I have logged into my account as standard user
    When I am on the contact list page
    Then all the elements looked for are being displayed
      | Zuständig           | contactTableView.selectedForPerson.select |
      | Status              | contactTableView.selectedTaskType.select  |
      | Titel               | contactTableView.title                    |
      | Kontaktperson       | contactTableView.selectedWithPerson       |
      | Kontaktfirma        | contactTableView.selectedWithCompany      |
      | Neuer Kontakt       | contactTableView.create                   |
      | Filter zurücksetzen | contactTableView.clear                    |
      | Suchen 1            | contactTableView.search1                  |
      | Suchen 2            | contactTableView.search2                  |

  Scenario: Check if it is possible to navigate the create contact page
    Given I have logged into my account as standard user
    And I am on the contact list page
    When I want to create a new contact
    Then the create contact page is being displayed

  Scenario: Check if it is possible to clear the search filter
    Given I have logged into my account as standard user
    And I am on the contact list page
    And I provide the following data to the contact search filter
      | selectedforperson   | Thorsten Prüser            |
      | selectedtasktype    | Offen                      |
      | title               | Ironman                    |
      | selectedwithperson  | Tony Stark                 |
      | selectedwithcompany | Stark Industries           |
    When I clear the contact search filter
    Then the contact search filter is empty

  Scenario: Check if the search results match the search filter criteria
    Given I have logged into my account as standard user
    And I am on the contact list page
    And I provide the following data to the contact search filter
      | selectedforperson   | Thorsten Prüser            |
      | selectedtasktype    | Abgeschlossen              |
      | title               | Akquise .NET               |
      | selectedwithperson  | Weckermann Michael (4329)  |
      | selectedwithcompany | E.H. Worlée & Co. (GmbH & Co. KG) (1989)  |
    When I list the contact search results
    Then the contact search results should only contain
      | Akquise .NET  | Thorsten Prüser | Michael Weckermann | E.H. Worlée & Co. (GmbH & Co. KG) |

  Scenario: Check if the search filters are correct
    Given I have logged into my account as standard user
    And I am on the contact list page
    And I provide the following data to the contact search filter
      | selectedwithcompany | Hammer Industries |
    When I list the contact search results
    Then the contact search results are empty