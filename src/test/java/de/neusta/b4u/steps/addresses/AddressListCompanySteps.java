package de.neusta.b4u.steps.addresses;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.addresses.AddressListCompanyItem;
import de.neusta.b4u.binding.addresses.AddressListCompanyPage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;

import java.util.List;

/**
 * Created by zih on 4/20/17.
 */
public class AddressListCompanySteps {
    private AddressListCompanyPage addressListCompanyPage = new AddressListCompanyPage();

    @And("^I am on the company address list page$")
    public void i_am_on_the_company_address_list_page() throws Throwable {
        addressListCompanyPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^I provide the following data to the company search filter$")
    public void i_provide_the_following_data_to_the_company_search_filter(DataTable searchFilter) throws Throwable {
        Assert.assertTrue("Company list page is displayed",
                addressListCompanyPage.isDisplayed(Context.getDriver()));

        for (List<String> row : searchFilter.raw()) {
            switch (row.get(0)) {
                case "nameorid":
                    addressListCompanyPage.enterNameOrId(Context.getDriver(), row.get(1));
                    break;

                case "phone":
                    addressListCompanyPage.enterPhone(Context.getDriver(), row.get(1));
                    break;

                case "zipcode":
                    addressListCompanyPage.enterZipCode(Context.getDriver(), row.get(1));
                    break;

                case "city":
                    addressListCompanyPage.enterCity(Context.getDriver(), row.get(1));
                    break;

                case "street":
                    addressListCompanyPage.enterStreet(Context.getDriver(), row.get(1));
                    break;

                case "skill":
                    addressListCompanyPage.enterSkill(Context.getDriver(), row.get(1));
                    break;

                case "forperson":
                    addressListCompanyPage.selectForPerson(Context.getDriver(), row.get(1));
                    break;
            }
        }
    }

    @When("^I clear the company search filter$")
    public void i_clear_the_company_search_filter() throws Throwable {
        Assert.assertTrue("Company list page is displayed",
                addressListCompanyPage.isDisplayed(Context.getDriver()));

        addressListCompanyPage.clickClearFilter(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the company search filter is empty$")
    public void the_company_search_filter_is_empty() throws Throwable {
        Assert.assertTrue("Company list page is displayed",
                addressListCompanyPage.isDisplayed(Context.getDriver()));

        Assert.assertTrue("Company search filter is empty",
                addressListCompanyPage.isSearchFormEmpty(Context.getDriver()));
    }

    @When("^I want to create a new company")
    public void i_want_to_create_a_new_company() throws Throwable {
        Assert.assertTrue("Company list page is displayed",
                addressListCompanyPage.isDisplayed(Context.getDriver()));

        addressListCompanyPage.clickCreateCompany(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I list the company search results$")
    public void i_list_the_company_search_results() throws Throwable {
        Assert.assertTrue("Company list page is displayed",
                addressListCompanyPage.isDisplayed(Context.getDriver()));

        addressListCompanyPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the company search results should only contain$")
    public void the_company_search_results_should_contain(DataTable desiredSearchResultTable) throws Throwable {
        Assert.assertTrue("Company list page is displayed",
                addressListCompanyPage.isDisplayed(Context.getDriver()));

        final List<List<String>> desiredSearchResults = desiredSearchResultTable.raw();
        final List<AddressListCompanyItem> searchResults = addressListCompanyPage.getSearchResultItems(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Company search results",
                desiredSearchResults.size(), searchResults.size());

        // compare each entries
        for (int i = 0;i < desiredSearchResults.size();++i) {
            final List<String> desiredSearchResult = desiredSearchResults.get(i);
            final AddressListCompanyItem searchResult = searchResults.get(i);

            Assert.assertEquals("ID", desiredSearchResult.get(0), searchResult.getID());
            Assert.assertEquals("Name", desiredSearchResult.get(1), searchResult.getName());
            Assert.assertEquals("Concern", desiredSearchResult.get(2), searchResult.getCorporationName());
            Assert.assertEquals("Street", desiredSearchResult.get(3), searchResult.getStreet());
            Assert.assertEquals("ZipCode", desiredSearchResult.get(4), searchResult.getZipCode());
            Assert.assertEquals("City", desiredSearchResult.get(5), searchResult.getCity());
            Assert.assertEquals("Phone", desiredSearchResult.get(6), searchResult.getPhone());
            Assert.assertEquals("KeyAccountManager", desiredSearchResult.get(7), searchResult.getKeyAccountManager());
        }
    }

    @Then("^the company search results are empty$")
    public void the_company_search_results_are_empty() throws Throwable {
        Assert.assertTrue("Company list page is displayed",
                addressListCompanyPage.isDisplayed(Context.getDriver()));

        final List<AddressListCompanyItem> searchResults = addressListCompanyPage.getSearchResultItems(Context.getDriver());

        // check if we have no entries
        Assert.assertEquals("Company search results are empty", 0, searchResults.size());
    }
}
