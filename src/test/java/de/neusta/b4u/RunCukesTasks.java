package de.neusta.b4u;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by malpen on 28.02.17.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/tasks"},
        strict = false,
        glue = {"de.neusta.b4u"})
public class RunCukesTasks extends Base {
}