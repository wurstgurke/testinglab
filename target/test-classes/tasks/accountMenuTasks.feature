# Acceptance criteria see https://portal.neusta.de/jira/browse/BFY-1064

Feature: [QA] Aufgaben
  Als B4U-Nutzer möchte ich unter dem Menüpunkt "Aufgaben" eine Suchmaske mit verschiedenen Suchattributen sehen,
  damit ich die gesuchte Aufgabe schneller finden kann

  @ugly
  Scenario: Check elements tasks
    Given I have logged into my account as standard user
    When I am on the task list page
    Then all the elements looked for are being displayed
      | Zuständig           | taskTableView.taskPersonList |
      | Aufgabentyp         | taskTableView.taskTypeList   |
      | Status              | taskTableView.taskStatusList |
      | Titel               | taskTableView.title          |
      | von                 | taskTableView.fromDate       |
      | bis                 | taskTableView.toDate         |
      | Filter zurücksetzen | taskTableView.clear          |
      | Suchen 1            | taskTableView.search1        |
      | Suchen 2            | taskTableView.search2        |

  @ugly
  Scenario: Check dropdown entries task type
    Given I have logged into my account as standard user
    When I am on the task list page
    And the dropdown menu "taskTableView.taskTypeList" contains the following items
      | - alle -      |
      | Anruf         |
      | Nachfrage     |
      | Wiedervorlage |
      | Besuch        |
      | Mail          |
      | Verträge      |

  @ugly
  Scenario: Check dropdown entries task status
    Given I have logged into my account as standard user
    When I am on the task list page
    And the dropdown menu "taskTableView.taskStatusList" contains the following items
      | - alle - |
      | Offen    |
      | Erledigt |

  @ugly
  Scenario Outline: Check menu entries tasks goals
    Given I have logged into my account as standard user
    When I am on the task list page
    And I click on the menu entry "<locator>"
    Then the target page is being displayed "<target page locator>"
    Examples:
      | Entry name | locator                 | target page locator |
      |            | navigationView.Aufgaben | /task/list          |

  Scenario: Check if it is possible to clear the search filter
    Given I have logged into my account as standard user
    And I am on the task list page
    And I provide the following data to the task search filter
      | taskperson | TestB4U Admin              |
      | tasktype   | Nachfrage                  |
      | taskstatus | Offen                      |
      | note       | Flash anwerben             |
      | fromdate   | 01.01.2017                 |
      | todate     | 31.12.2017                 |
    When I clear the task search filter
    Then the task search filter is empty

  Scenario: Create a new task
    Given I have logged into my account as standard user
    And I have ensured that the contact "Justice League" already exist
    And I am on the contact edit page of "Justice League"
    When I create a new task "Flash anwerben"
      | type      | Nachfrage              |
      | priority  | hoch                   |
      | reminder  | täglich                |
      | startdate | 01.02.2017             |
      | enddate   | 02.03.2017             |
    Then the task "Flash anwerben" is created
    And I am on the contact edit page of "Justice League"
    And the task details of "Flash anwerben" are
      | type      | Nachfrage              |
      | priority  | hoch                   |
      | reminder  | täglich                |
      | startdate | 01.02.2017             |
      | enddate   | 02.03.2017             |
      | note      | Flash anwerben         |

  Scenario: Check if the search results match the search filter criteria
    Given I have logged into my account as standard user
    And I am on the task list page
    And I provide the following data to the task search filter
      | taskperson | TestB4U User               |
      | tasktype   | Nachfrage                  |
      | taskstatus | Offen                      |
      | note       | Flash anwerben             |
      | fromdate   | 01.01.2017                 |
      | todate     | 31.12.2017                 |
    When I list the task search results
    Then the task search results should only contain
      | Flash anwerben | TestB4U User | 01.02.2017 | 02.03.2017 | Nachfrage | täglich |

  Scenario: Check if the search filters are correct
    Given I have logged into my account as standard user
    And I am on the task list page
    And I provide the following data to the task search filter
      | taskperson | TestB4U User               |
      | tasktype   | Nachfrage                  |
      | note       | Spiderman anwerben         |
    When I list the task search results
    Then the task search results are empty

  Scenario: Edit an existing task
    Given I have logged into my account as standard user
    And I have ensured that the contact "Justice League" already exist
    And I am on the contact edit page of "Justice League"
    When I edit the task "Flash anwerben"
      | type      | Wiedervorlage          |
      | priority  | mittel                 |
      | reminder  | wöchentlich            |
      | startdate | 02.03.2017             |
      | enddate   | 03.04.2017             |
    Then the task details of "Flash anwerben" are
      | type      | Wiedervorlage          |
      | priority  | mittel                 |
      | reminder  | wöchentlich            |
      | startdate | 02.03.2017             |
      | enddate   | 03.04.2017             |

  Scenario: Delete Task
    Given I have logged into my account as standard user
    And I have ensured that the contact "Justice League" already exist
    And I am on the contact edit page of "Justice League"
    When I delete the task "Flash anwerben"
    Then the task "Flash anwerben" is deleted
