$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("login.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "# Akzeptanzkriterien siehe https://portal.neusta.de/jira/browse/BFY-1040"
    }
  ],
  "line": 3,
  "name": "[QA] Login",
  "description": "Als B4U-Nutzer möchte ich mich in meinen B4U-Account einloggen können, damit ich mit meinem B4U-Account arbeiten kann",
  "id": "[qa]-login",
  "keyword": "Feature",
  "tags": [
    {
      "line": 2,
      "name": "@focus"
    }
  ]
});
formatter.scenarioOutline({
  "line": 7,
  "name": "Ensure that all necessary login fields are displayed",
  "description": "",
  "id": "[qa]-login;ensure-that-all-necessary-login-fields-are-displayed",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 6,
      "name": "@ugly"
    }
  ]
});
formatter.step({
  "line": 8,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "I look for the element \"\u003clocator\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "the element looked for is being displayed",
  "keyword": "Then "
});
formatter.examples({
  "line": 11,
  "name": "",
  "description": "",
  "id": "[qa]-login;ensure-that-all-necessary-login-fields-are-displayed;",
  "rows": [
    {
      "cells": [
        "field",
        "locator"
      ],
      "line": 12,
      "id": "[qa]-login;ensure-that-all-necessary-login-fields-are-displayed;;1"
    },
    {
      "cells": [
        "Username",
        "loginView.userName"
      ],
      "line": 13,
      "id": "[qa]-login;ensure-that-all-necessary-login-fields-are-displayed;;2"
    },
    {
      "cells": [
        "Password",
        "loginView.password"
      ],
      "line": 14,
      "id": "[qa]-login;ensure-that-all-necessary-login-fields-are-displayed;;3"
    },
    {
      "cells": [
        "Anmelden",
        "loginView.login"
      ],
      "line": 15,
      "id": "[qa]-login;ensure-that-all-necessary-login-fields-are-displayed;;4"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 2391229472,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Ensure that all necessary login fields are displayed",
  "description": "",
  "id": "[qa]-login;ensure-that-all-necessary-login-fields-are-displayed;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 6,
      "name": "@ugly"
    },
    {
      "line": 2,
      "name": "@focus"
    }
  ]
});
formatter.step({
  "line": 8,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "I look for the element \"loginView.userName\"",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "the element looked for is being displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginSteps.i_am_on_the_login_page()"
});
formatter.result({
  "duration": 5982026765,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "loginView.userName",
      "offset": 24
    }
  ],
  "location": "BaseSteps.i_look_for_the_element(String)"
});
formatter.result({
  "duration": 44334434,
  "status": "passed"
});
formatter.match({
  "location": "BaseSteps.the_element_looked_for_is_being_displayed()"
});
formatter.result({
  "duration": 1939634,
  "status": "passed"
});
formatter.after({
  "duration": 131822984,
  "status": "passed"
});
formatter.before({
  "duration": 955682935,
  "status": "passed"
});
formatter.scenario({
  "line": 14,
  "name": "Ensure that all necessary login fields are displayed",
  "description": "",
  "id": "[qa]-login;ensure-that-all-necessary-login-fields-are-displayed;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 6,
      "name": "@ugly"
    },
    {
      "line": 2,
      "name": "@focus"
    }
  ]
});
formatter.step({
  "line": 8,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "I look for the element \"loginView.password\"",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "the element looked for is being displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginSteps.i_am_on_the_login_page()"
});
formatter.result({
  "duration": 5740946928,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "loginView.password",
      "offset": 24
    }
  ],
  "location": "BaseSteps.i_look_for_the_element(String)"
});
formatter.result({
  "duration": 44079945,
  "status": "passed"
});
formatter.match({
  "location": "BaseSteps.the_element_looked_for_is_being_displayed()"
});
formatter.result({
  "duration": 17746,
  "status": "passed"
});
formatter.after({
  "duration": 73912246,
  "status": "passed"
});
formatter.before({
  "duration": 870754762,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "Ensure that all necessary login fields are displayed",
  "description": "",
  "id": "[qa]-login;ensure-that-all-necessary-login-fields-are-displayed;;4",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 6,
      "name": "@ugly"
    },
    {
      "line": 2,
      "name": "@focus"
    }
  ]
});
formatter.step({
  "line": 8,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "I look for the element \"loginView.login\"",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "the element looked for is being displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginSteps.i_am_on_the_login_page()"
});
formatter.result({
  "duration": 5727209397,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "loginView.login",
      "offset": 24
    }
  ],
  "location": "BaseSteps.i_look_for_the_element(String)"
});
formatter.result({
  "duration": 27538119,
  "status": "passed"
});
formatter.match({
  "location": "BaseSteps.the_element_looked_for_is_being_displayed()"
});
formatter.result({
  "duration": 19634,
  "status": "passed"
});
formatter.after({
  "duration": 70249332,
  "status": "passed"
});
formatter.before({
  "duration": 994779187,
  "status": "passed"
});
formatter.scenario({
  "line": 17,
  "name": "Login success [standard user]",
  "description": "",
  "id": "[qa]-login;login-success-[standard-user]",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 16,
      "name": "@focus"
    }
  ]
});
formatter.step({
  "line": 18,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 19,
  "name": "I provide the standard user credentials",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "I login",
  "keyword": "When "
});
formatter.step({
  "line": 21,
  "name": "the account page is being displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginSteps.i_am_on_the_login_page()"
});
formatter.result({
  "duration": 5722144902,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_the_standard_user_credentials()"
});
formatter.result({
  "duration": 390094841,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_login()"
});
formatter.result({
  "duration": 1588759865,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.the_account_page_is_being_displayed()"
});
formatter.result({
  "duration": 30148337,
  "status": "passed"
});
formatter.after({
  "duration": 71716612,
  "status": "passed"
});
formatter.before({
  "duration": 887384564,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Login success [admin user]",
  "description": "",
  "id": "[qa]-login;login-success-[admin-user]",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 24,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 25,
  "name": "I provide the admin user credentials",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "I login",
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "the account page is being displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginSteps.i_am_on_the_login_page()"
});
formatter.result({
  "duration": 5780456254,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_the_admin_user_credentials()"
});
formatter.result({
  "duration": 401417363,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_login()"
});
formatter.result({
  "duration": 972847014,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.the_account_page_is_being_displayed()"
});
formatter.result({
  "duration": 1024713812,
  "error_message": "java.lang.AssertionError: Account page is displayed\r\n\tat org.junit.Assert.fail(Assert.java:88)\r\n\tat org.junit.Assert.assertTrue(Assert.java:41)\r\n\tat de.neusta.b4u.steps.login.LoginSteps.the_account_page_is_being_displayed(LoginSteps.java:97)\r\n\tat ✽.Then the account page is being displayed(login.feature:27)\r\n",
  "status": "failed"
});
formatter.after({
  "duration": 70076022,
  "status": "passed"
});
formatter.before({
  "duration": 851574400,
  "status": "passed"
});
formatter.scenario({
  "line": 29,
  "name": "Login failure wrong username [standard user]",
  "description": "",
  "id": "[qa]-login;login-failure-wrong-username-[standard-user]",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 30,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 31,
  "name": "I provide the standard user credentials",
  "keyword": "And "
});
formatter.step({
  "line": 32,
  "name": "I provide an invalid username",
  "keyword": "And "
});
formatter.step({
  "line": 33,
  "name": "I login",
  "keyword": "When "
});
formatter.step({
  "line": 34,
  "name": "an error message is being displayed \"is-error\"",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginSteps.i_am_on_the_login_page()"
});
formatter.result({
  "duration": 5954805436,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_the_standard_user_credentials()"
});
formatter.result({
  "duration": 348741787,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_an_invalid_username()"
});
formatter.result({
  "duration": 251473510,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_login()"
});
formatter.result({
  "duration": 987026315,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "is-error",
      "offset": 37
    }
  ],
  "location": "BaseSteps.an_error_message_is_being_displayed(String)"
});
formatter.result({
  "duration": 28948762,
  "status": "passed"
});
formatter.after({
  "duration": 70281049,
  "status": "passed"
});
formatter.before({
  "duration": 804721500,
  "status": "passed"
});
formatter.scenario({
  "line": 36,
  "name": "Login failure wrong username [admin user]",
  "description": "",
  "id": "[qa]-login;login-failure-wrong-username-[admin-user]",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 37,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 38,
  "name": "I provide the admin user credentials",
  "keyword": "And "
});
formatter.step({
  "line": 39,
  "name": "I provide an invalid username",
  "keyword": "And "
});
formatter.step({
  "line": 40,
  "name": "I login",
  "keyword": "When "
});
formatter.step({
  "line": 41,
  "name": "an error message is being displayed \"is-error\"",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginSteps.i_am_on_the_login_page()"
});
formatter.result({
  "duration": 5950278617,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_the_admin_user_credentials()"
});
formatter.result({
  "duration": 358453926,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_an_invalid_username()"
});
formatter.result({
  "duration": 276766154,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_login()"
});
formatter.result({
  "duration": 1004602713,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "is-error",
      "offset": 37
    }
  ],
  "location": "BaseSteps.an_error_message_is_being_displayed(String)"
});
formatter.result({
  "duration": 32793669,
  "status": "passed"
});
formatter.after({
  "duration": 62706395,
  "status": "passed"
});
formatter.before({
  "duration": 831187288,
  "status": "passed"
});
formatter.scenario({
  "line": 43,
  "name": "Login failure wrong password [standard user]",
  "description": "",
  "id": "[qa]-login;login-failure-wrong-password-[standard-user]",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 44,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 45,
  "name": "I provide the standard user credentials",
  "keyword": "And "
});
formatter.step({
  "line": 46,
  "name": "I provide an invalid password",
  "keyword": "And "
});
formatter.step({
  "line": 47,
  "name": "I login",
  "keyword": "When "
});
formatter.step({
  "line": 48,
  "name": "an error message is being displayed \"is-error\"",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginSteps.i_am_on_the_login_page()"
});
formatter.result({
  "duration": 5665183024,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_the_standard_user_credentials()"
});
formatter.result({
  "duration": 406479215,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_an_invalid_password()"
});
formatter.result({
  "duration": 222236276,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_login()"
});
formatter.result({
  "duration": 952100824,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "is-error",
      "offset": 37
    }
  ],
  "location": "BaseSteps.an_error_message_is_being_displayed(String)"
});
formatter.result({
  "duration": 24111948,
  "status": "passed"
});
formatter.after({
  "duration": 64084943,
  "status": "passed"
});
formatter.before({
  "duration": 844142472,
  "status": "passed"
});
formatter.scenario({
  "line": 50,
  "name": "Login failure wrong password [admin user]",
  "description": "",
  "id": "[qa]-login;login-failure-wrong-password-[admin-user]",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 51,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 52,
  "name": "I provide the admin user credentials",
  "keyword": "And "
});
formatter.step({
  "line": 53,
  "name": "I provide an invalid password",
  "keyword": "And "
});
formatter.step({
  "line": 54,
  "name": "I login",
  "keyword": "When "
});
formatter.step({
  "line": 55,
  "name": "an error message is being displayed \"is-error\"",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginSteps.i_am_on_the_login_page()"
});
formatter.result({
  "duration": 5741986409,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_the_admin_user_credentials()"
});
formatter.result({
  "duration": 427309229,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_an_invalid_password()"
});
formatter.result({
  "duration": 302255895,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_login()"
});
formatter.result({
  "duration": 973309173,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "is-error",
      "offset": 37
    }
  ],
  "location": "BaseSteps.an_error_message_is_being_displayed(String)"
});
formatter.result({
  "duration": 26432183,
  "status": "passed"
});
formatter.after({
  "duration": 70452093,
  "status": "passed"
});
formatter.before({
  "duration": 963788467,
  "status": "passed"
});
formatter.scenario({
  "line": 57,
  "name": "Login failure wrong username wrong password",
  "description": "",
  "id": "[qa]-login;login-failure-wrong-username-wrong-password",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 58,
  "name": "I am on the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 59,
  "name": "I provide an invalid username",
  "keyword": "And "
});
formatter.step({
  "line": 60,
  "name": "I provide an invalid password",
  "keyword": "And "
});
formatter.step({
  "line": 61,
  "name": "I login",
  "keyword": "When "
});
formatter.step({
  "line": 62,
  "name": "an error message is being displayed \"is-error\"",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginSteps.i_am_on_the_login_page()"
});
formatter.result({
  "duration": 5788451155,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_an_invalid_username()"
});
formatter.result({
  "duration": 428996261,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_provide_an_invalid_password()"
});
formatter.result({
  "duration": 274415712,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.i_login()"
});
formatter.result({
  "duration": 997592166,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "is-error",
      "offset": 37
    }
  ],
  "location": "BaseSteps.an_error_message_is_being_displayed(String)"
});
formatter.result({
  "duration": 24031524,
  "status": "passed"
});
formatter.after({
  "duration": 64156307,
  "status": "passed"
});
});