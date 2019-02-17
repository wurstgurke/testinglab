package de.neusta.b4u.steps.menu;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created by zih on 5/4/17.
 */
public class MenuSteps {
    @When("^I look at the menu entries$")
    public void i_look_at_the_menu_entries() throws Throwable {
        // This function is intentionally left empty
    }

    @When("^I look at the menu entry \"([^\"]*)\"$")
    public void i_look_at_the_menu_entry(String arg1) throws Throwable {
        Actions actions = new Actions(Context.getDriver());
        actions.moveToElement(Context.getDriver().findElement(By.id(arg1))).build().perform();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^all the menu entries looked for are being displayed$")
    public void all_the_menu_entries_looked_for_are_being_displayed(DataTable arg1) throws Throwable {
        List<List<String>> dataTable = arg1.raw();
        for (List<String> l : dataTable) {
            WebElement element = Context.getDriver().findElement(By.id(l.get(1)));
            Assert.assertTrue(l.get(0) + " is displayed", element.getText().equals(l.get(0)));
        }
    }

    @Then("^all the menu entries looked for are not being displayed$")
    public void all_the_menu_entries_looked_for_are_not_being_displayed(DataTable arg1) throws Throwable {
        List<List<String>> dataTable = arg1.raw();
        for (List<String> l : dataTable) {
            Assert.assertFalse(l.get(0) + " is not displayed", SeleniumHelper.elementExists(Context.getDriver(), By.id(l.get(1))));
        }
    }

    @When("^I click on the menu entry \"([^\"]*)\"$")
    public void i_click_on_the_menu_entry(String arg1) throws Throwable {
        Context.getDriver().findElement(By.id(arg1)).click();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the target page is being displayed \"([^\"]*)\"$")
    public void the_target_page_is_being_displayed(String arg1) throws Throwable {
        Assert.assertTrue(arg1 + "is displayed", SeleniumHelper.elementExists(Context.getDriver(), By.id(arg1)));
    }

    @Then("^all the onmouse over entries looked for are being displayed$")
    public void all_the_onmouse_over_entries_looked_for_are_being_displayed(DataTable arg1) throws Throwable {
        List<List<String>> list = arg1.raw();
        for (List<String> l : list) {
            WebElement element = Context.getDriver().findElement(By.id(l.get(1)));
            Assert.assertTrue(l.get(0) + "is displayed", l.get(0).equals(element.getText()));
        }
    }

    @When("^I select the menu entry \"([^\"]*)\"$")
    public void i_select_the_menu_entry(String arg1) throws Throwable {
        Context.getDriver().findElement(By.id(arg1)).click();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }
}
