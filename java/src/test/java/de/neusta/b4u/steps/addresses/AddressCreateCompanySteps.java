package de.neusta.b4u.steps.addresses;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.addresses.*;
import de.neusta.b4u.binding.addresses.AddressCreateCompanyPage;
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
public class AddressCreateCompanySteps {
    private AddressCreateCompanyPage addressCreateCompanyPage = new AddressCreateCompanyPage();

    @Then("^the create company page is being displayed$")
    public void the_create_company_page_is_being_displayed() throws Throwable {
        Assert.assertTrue("Company create page is displayed",
                addressCreateCompanyPage.isDisplayed(Context.getDriver()));
    }

    @When("^I am on the create new company page$")
    public void i_am_on_the_create_new_company_page() throws Throwable {
        addressCreateCompanyPage.open(Context.getDriver());
    }

    @And("^I have checked that the company \"([^\"]*)\" does not already exist$")
    public void i_have_checked_that_the_company_does_not_already_exist(String companyName) throws Throwable {
        AddressListCompanyPage addressListCompanyPage = new AddressListCompanyPage();
        addressListCompanyPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Company list page is displayed",
                addressListCompanyPage.isDisplayed(Context.getDriver()));

        addressListCompanyPage.enterNameOrId(Context.getDriver(), companyName);
        addressListCompanyPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // get search results
        List<AddressListCompanyItem> searchResults = addressListCompanyPage.getSearchResultItems(Context.getDriver());
        for (AddressListCompanyItem searchResult : searchResults) {
            if (searchResult.getName().equals(companyName)) {
                // delete the item
                searchResult.clickRemove();

                SeleniumHelper.acceptAlert(Context.getDriver());
                SeleniumHelper.waitForAngularFinished(Context.getDriver());

                break;
            }
        }
    }

    @When("^I create a new simple company \"([^\"]*)\"$")
    public void i_create_a_new_simple_company(String companyName) throws Throwable {
        addressCreateCompanyPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Company create page is displayed",
                addressCreateCompanyPage.isDisplayed(Context.getDriver()));

        List<String> name = Arrays.asList("name", companyName);
        List<List<String>> companyDataRaw = new ArrayList<>();
        companyDataRaw.add(0, name);

        // apply company details
        AddressStepHelper.applyCompanyDetails(addressCreateCompanyPage, companyDataRaw);

        Thread.sleep(500);

        // save the company
        addressCreateCompanyPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I create a new company \"([^\"]*)\"$")
    public void i_create_a_new_company(String companyName, DataTable companyData) throws Throwable {
        addressCreateCompanyPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Company create page is displayed",
                addressCreateCompanyPage.isDisplayed(Context.getDriver()));

        List<String> name = Arrays.asList("name", companyName);
        List<List<String>> companyDataRaw = new ArrayList<>(companyData.raw());
        companyDataRaw.add(0, name);

        // apply company details
        AddressStepHelper.applyCompanyDetails(addressCreateCompanyPage, companyDataRaw);

        Thread.sleep(500);

        // save the company
        addressCreateCompanyPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the company \"([^\"]*)\" is created$")
    public void the_company_is_created(String companyName) throws Throwable {
        AddressListCompanyPage addressListCompanyPage = new AddressListCompanyPage();
        addressListCompanyPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Company list page is displayed",
                addressListCompanyPage.isDisplayed(Context.getDriver()));

        addressListCompanyPage.enterNameOrId(Context.getDriver(), companyName);
        addressListCompanyPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<AddressListCompanyItem> companyItems = addressListCompanyPage.getSearchResultItems(Context.getDriver());
        Assert.assertFalse("Company list is empty", companyItems.isEmpty());
    }
}
