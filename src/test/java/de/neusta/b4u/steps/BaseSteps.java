package de.neusta.b4u.steps;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Base;
import de.neusta.b4u.Context;
import de.neusta.b4u.helper.PropertyHelper;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created by zih on 4/6/17.
 */
public class BaseSteps extends Base {
    private WebElement element = null;

    @Before
    public void setUp() throws Exception {
        final String browser = PropertyHelper.getCustomProperty("testProperties", "selenium.browser");

        WebDriver driver = getDriver(browser);
        Context.init(driver);
        element = null;
    }

    @After
    public void tearDown() {
        quitDriver(Context.getDriver());
        Context.clear();
        element = null;
    }

    @When("^I look for the element \"(.*?)\"$")
    public void i_look_for_the_element(String arg1) throws Throwable {
        try {
            element = Context.getDriver().findElement(By.id(arg1));
        } catch (NoSuchElementException e) {
            //
        }
    }

    @Then("^the element looked for is being displayed$")
    public void the_element_looked_for_is_being_displayed() throws Throwable {
        Assert.assertTrue(element != null);
    }

    @Then("^an error message is being displayed \"([^\"]*)\"$")
    public void an_error_message_is_being_displayed(String arg1) throws Throwable {
        Assert.assertTrue("Error message is displayed", SeleniumHelper.elementExists(Context.getDriver(), By.className(arg1)));
    }

    @Then("^all the elements looked for are being displayed$")
    public void all_the_elements_looked_for_are_being_displayed(DataTable arg1) throws Throwable {
        List<List<String>> dataTable = arg1.raw();
        for (List<String> l : dataTable) {
            Assert.assertTrue(l.get(1) + " is displayed", SeleniumHelper.elementExists(Context.getDriver(), By.id(l.get(1))));
        }
    }

    @Then("^the dropdown menu \"([^\"]*)\" contains the following items$")
    public void the_dropdown_menu_contains_the_following_items(String dropDown, DataTable desiredItems) throws Throwable {
        WebElement dropDownElement = Context.getDriver().findElement(By.id(dropDown));
        dropDownElement.click(); // dropdown box has to be opened

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<WebElement> dropDownItems = dropDownElement.findElements(By.className("ui-select-choices-row"));
        List<List<String>> desiredItemsList = desiredItems.raw();

        Assert.assertEquals("Dropdown size", desiredItemsList.size(), dropDownItems.size());
        for (int i = 0; i < desiredItemsList.size(); i++) {
            final String itemText = dropDownItems.get(i).getText();
            final String desiredText = desiredItemsList.get(i).get(0);

            Assert.assertEquals("Dropdown element", desiredText, itemText);
        }
    }

    @When("^I click on the button \"([^\"]*)\"$")
    public void i_click_on_the_button(String arg1) throws Throwable {
        Context.getDriver().findElement(By.id(arg1)).click();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }
}
