package de.neusta.b4u;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * Created by malpen on 14.02.17.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/login",
                "src/test/resources/addresses",
                "src/test/resources/administration",
                "src/test/resources/jobadvertisement",
                "src/test/resources/candidacies",
                "src/test/resources/contact",
                "src/test/resources/email",
                "src/test/resources/menu",
                "src/test/resources/tasks",
                "src/test/resources/profile"
        },
        strict = false,
        glue = {"de.neusta.b4u"},
        tags = "@focus")
public class RunCukesTest extends Base {
}
