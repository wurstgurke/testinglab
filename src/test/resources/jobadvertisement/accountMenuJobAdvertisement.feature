# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1063

Feature: [QA] Ausschreibungen
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Ausschreibungen" eine Suchmaske mit verschiedenen Suchattributen sehen,
  damit ich die gesuchte Ausschreibung schneller finden kann

  @ugly
  Scenario Outline: Check menu entries job advertisement goals
    Given I have logged into my account as standard user
    When I look at the menu entry "navigationView.Ausschreibungen"
    And I click on the menu entry "<locator>"
    Then the target page is being displayed "<target page locator>"
    Examples:
      | Entry name | locator                        | target page locator |
      |            | navigationView.Ausschreibungen | /job/list           |

  @ugly
  Scenario: Check elements job advertisement
    Given I have logged into my account as standard user
    When I am on the job advertisement list page
    Then all the elements looked for are being displayed
      | Ausschr.ID          | jobAdvertisementListView.id                |
      | Mitarbeiter         | jobAdvertisementListView.selectedForPerson |
      | Position            | jobAdvertisementListView.position          |
      | Status              | jobAdvertisement.stateTypes                |
      | geändert am von     | jobAdvertisementListView.startDate         |
      | geändert am bis     | jobAdvertisementListView.endDate           |
      | Suchen              | jobAdvertisementListView.search1           |
      | Suchen              | jobAdvertisementListView.search2           |
      | Filter zurücksetzen | jobAdvertisementListView.clear             |
      | Veröffentlichen     | jobAdvertisementListView.publish           |

  @ugly
  Scenario: Check dropdown entries job advertisement
    Given I have logged into my account as standard user
    When I am on the job advertisement list page
    Then the dropdown menu "jobAdvertisement.stateTypes" contains the following items
      | - alle -       |
      | veröffentlicht |
      | gewonnen       |
      | erledigt       |

  Scenario: Check if it is possible to clear the search filter
    Given I have logged into my account as standard user
    And I am on the job advertisement list page
    And I provide the following data to the job advertisement search filter
      | id         | 00001                           |
      | forperson  | Thorsten Prüser                 |
      | position   | Java Webclient Entwickler (w/m) |
      | state      | - alle -                        |
      | startdate  | 01.01.2017                      |
      | enddate    | 31.12.2017                      |
    When I clear the job advertisement search filter
    Then the job advertisement filter is empty

  Scenario: Check if the search results match the search filter criteria
    Given I have logged into my account as standard user
    And I am on the job advertisement list page
    And I provide the following data to the job advertisement search filter
      | id         | 00001                           |
      | forperson  | Stefanie Wolkowski              |
      | position   | Java Webclient Entwickler (w/m) |
      | state      | - alle -                        |
      | fromdate   | 01.01.2017                      |
      | todate     | 31.12.2017                      |
    When I list the job advertisement search results
    Then the job advertisement search results should only contain
      | 30.03.2015 | 00001 | Java Webclient Entwickler (w/m) | | EE_20150330_capgemini_Java Webclient Entwickler | 25.05.2015 - 11.09.2015 |  Stefanie Wolkowski | Stefanie Wolkowski |

  Scenario: Check if the search filters are correct
    Given I have logged into my account as standard user
    And I am on the job advertisement list page
    And I provide the following data to the job advertisement search filter
      | id         | 00000                            |
      | position   | Cobol Webclient Entwickler (w/m) |
    When I list the job advertisement search results
    Then the job advertisement search results are empty

  Scenario: Create a new job advertisement
    Given I have logged into my account as standard user
    And I have ensured that the contact "Justice League" already exist
    And I have checked that the job advertisement "Superheld mit besonderen Fähigkeiten (m/w)" does not already exist
    And I am on the contact edit page of "Justice League"
    When I create a new job advertisement "Superheld mit besonderen Fähigkeiten"
      | forperson        | TestB4U User                               |
      | position         | Superheld mit besonderen Fähigkeiten (m/w) |
      | shortdescription | I'm Batman             |
      | taskdescription  | I'm Batman             |
      | skills           | I'm Batman             |
      | notes            | I'm Batman             |
      | keywords         | batman, bruce, wayne   |
      | zipcode          | 31337                  |
      | city             | Gotham City            |
      | country          | Deutschland            |
      | federalstate     | Bremen                 |
      | location         | Überseestadt           |
      | startdate        | 01.01.2030             |
      | enddate          | 31.12.2030             |
      | duration         | 6 Monate               |
    Then the job advertisement "Superheld mit besonderen Fähigkeiten" is created
    And I am on the job advertisement edit page of "Superheld mit besonderen Fähigkeiten (m/w)"
    And the job advertisement details are
      | name             | Superheld mit besonderen Fähigkeiten       |
      | forperson        | TestB4U User                               |
      | position         | Superheld mit besonderen Fähigkeiten (m/w) |
      | shortdescription | I'm Batman             |
      | taskdescription  | I'm Batman             |
      | skills           | I'm Batman             |
      | notes            | I'm Batman             |
      | keywords         | batman, bruce, wayne   |
      | zipcode          | 31337                  |
      | city             | Gotham City            |
      | country          | Deutschland            |
      | federalstate     | Bremen                 |
      | location         | Überseestadt           |
      | startdate        | 01.01.2030             |
      | enddate          | 31.12.2030             |
      | duration         | 6 Monate               |

  Scenario: Check if the selected note is shown when creating a new job advertisement
    Given I have logged into my account as standard user
    And I have ensured that the contact "Justice League" already exist
    And I am on the contact edit page of "Justice League"
    And I add a new note to the contact "I am Batman!!!"
    And I select the contact note "I am Batman!!!"
    When I try to create a new job advertisement
    Then the job advertisement popup displays the note "I am Batman!!!"

  Scenario: Edit an existing job advertisement
    Given I have logged into my account as standard user
    And I am on the job advertisement edit page of "Superheld mit besonderen Fähigkeiten (m/w)"
    When I edit the job advertisement details
      | name             | Pizzalieferant           |
      | forperson        | TestB4U User             |
      | position         | Superheld mit besonderen Fähigkeiten (m/w) |
      | shortdescription | I'm Spiderman            |
      | taskdescription  | I'm Spiderman            |
      | skills           | I'm Spiderman            |
      | notes            | I'm Spiderman            |
      | keywords         | spiderman, peter, parker |
      | zipcode          | 12345                    |
      | city             | New York                 |
      | country          | Deutschland              |
      | federalstate     | Niedersachsen            |
      | location         | Unterseestadt            |
      | startdate        | 01.01.2031               |
      | enddate          | 31.12.2031               |
      | duration         | 3 Wochen                 |
    Then the job advertisement details are
      | name             | Pizzalieferant           |
      | forperson        | TestB4U User             |
      | position         | Superheld mit besonderen Fähigkeiten (m/w) |
      | shortdescription | I'm Spiderman            |
      | taskdescription  | I'm Spiderman            |
      | skills           | I'm Spiderman            |
      | notes            | I'm Spiderman            |
      | keywords         | spiderman, peter, parker |
      | zipcode          | 12345                    |
      | city             | New York                 |
      | country          | Deutschland              |
      | federalstate     | Niedersachsen            |
      | location         | Unterseestadt            |
      | startdate        | 01.01.2031               |
      | enddate          | 31.12.2031               |
      | duration         | 3 Wochen                 |

  Scenario: Delete job advertisement
    Given I have logged into my account as standard user
    And I am on the job advertisement list page
    When I delete the job advertisement "Superheld mit besonderen Fähigkeiten (m/w)"
    Then the job advertisement "Superheld mit besonderen Fähigkeiten (m/w)" is deleted

  Scenario: Add an applicant to an existing job advertisement
    Given I have logged into my account as standard user
    And I have checked that the job advertisement "Superheld mit besonderen Fähigkeiten (m/w)" does not already exist
    And I have ensured that the contact "Justice League" already exist
    And I am on the contact edit page of "Justice League"
    And I create a new job advertisement "Superheld mit besonderen Fähigkeiten"
      | position         | Superheld mit besonderen Fähigkeiten (m/w) |
    And I have checked that the person "Wayne, Bruce" does not already exist
    And I create a new person "Bruce Wayne"
      | salutation    | Herr                                    |
      | contacttype   | Interessent                             |
    When I am on the job advertisement edit page of "Superheld mit besonderen Fähigkeiten (m/w)"
    And I add the applicant "Bruce Wayne" to the job advertisement
    Then the job advertisement applicant list contains
      | Bruce | Wayne | |