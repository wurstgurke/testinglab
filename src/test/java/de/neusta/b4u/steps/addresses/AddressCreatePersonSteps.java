package de.neusta.b4u.steps.addresses;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.addresses.*;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zih on 4/20/17.
 */
public class AddressCreatePersonSteps {
    private AddressCreatePersonPage addressCreatePersonPage = new AddressCreatePersonPage();

    @Then("^the create person page is being displayed$")
    public void the_create_person_page_is_being_displayed() throws Throwable {
        Assert.assertTrue("Person create page is displayed",
                addressCreatePersonPage.isDisplayed(Context.getDriver()));
    }

    @When("^I am on the create new person page$")
    public void i_am_on_the_create_new_person_page() throws Throwable {
        addressCreatePersonPage.open(Context.getDriver());
    }

    @And("^I have checked that the person \"([^\"]*)\" does not already exist$")
    public void i_have_checked_that_the_person_does_not_already_exist(String personName) throws Throwable {
        AddressListPersonPage addressListPersonPage = new AddressListPersonPage();
        addressListPersonPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        addressListPersonPage.enterNameOrId(Context.getDriver(), personName);
        addressListPersonPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // get search results
        List<AddressListPersonItem> searchResults = addressListPersonPage.getSearchResultItems(Context.getDriver(), false);
        for (AddressListPersonItem searchResult : searchResults) {
            if (searchResult.getName().equals(personName)) {
                // delete the item
                searchResult.clickRemove();

                SeleniumHelper.acceptAlert(Context.getDriver());
                SeleniumHelper.waitForAngularFinished(Context.getDriver());

                break;
            }
        }
    }

    @When("^I create a new person \"([^\"]*)\"$")
    public void i_create_a_new_person(String personName, DataTable personData) throws Throwable {
        addressCreatePersonPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Person create page is displayed",
                addressCreatePersonPage.isDisplayed(Context.getDriver()));

        final String[] split = personName.split(" ");
        List<String> firstName = Arrays.asList("firstname", split[0]);
        List<String> lastName = Arrays.asList("lastname", split[1]);

        List<List<String>> personDataRaw = new ArrayList<>(personData.raw());
        personDataRaw.add(0, firstName);
        personDataRaw.add(0, lastName);

        // apply person details
        AddressStepHelper.applyPersonDetails(addressCreatePersonPage, personDataRaw);

        // save the person
        addressCreatePersonPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the person \"([^\"]*)\" is created$")
    public void the_person_is_created(String personName) throws Throwable {
        AddressListPersonPage addressListPersonPage = new AddressListPersonPage();
        addressListPersonPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        addressListPersonPage.enterNameOrId(Context.getDriver(), personName);
        addressListPersonPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<AddressListPersonItem> personItems = addressListPersonPage.getSearchResultItems(Context.getDriver(), false);
        Assert.assertFalse("Person list is empty", personItems.isEmpty());
    }
}
