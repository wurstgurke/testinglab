package de.neusta.b4u.steps.addresses;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.addresses.AddressListFreelancerItem;
import de.neusta.b4u.binding.addresses.AddressListPersonItem;
import de.neusta.b4u.binding.addresses.AddressListPersonPage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

/**
 * Created by zih on 4/20/17.
 */
public class AddressListPersonSteps {
    private AddressListPersonPage addressListPersonPage = new AddressListPersonPage();

    @And("^I am on the person address list page$")
    public void i_am_on_the_person_address_list_page() throws Throwable {
        addressListPersonPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^I provide the following data to the person search filter$")
    public void i_provide_the_following_data_to_the_person_search_filter(DataTable searchFilter) throws Throwable {
        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        // FIXME: dirty hack to get the edit button clickable
        ((JavascriptExecutor) Context.getDriver()).executeScript("window.scrollTo(0,0);");

        for (List<String> row : searchFilter.raw()) {
            switch (row.get(0)) {
                case "nameorid":
                    addressListPersonPage.enterNameOrId(Context.getDriver(), row.get(1));
                    break;

                case "phone":
                    addressListPersonPage.enterPhone(Context.getDriver(), row.get(1));
                    break;

                case "email":
                    addressListPersonPage.enterEmail(Context.getDriver(), row.get(1));
                    break;

                case "company":
                    addressListPersonPage.enterCompany(Context.getDriver(), row.get(1));
                    break;

                case "zipcode":
                    addressListPersonPage.enterZipCode(Context.getDriver(), row.get(1));
                    break;

                case "city":
                    addressListPersonPage.enterCity(Context.getDriver(), row.get(1));
                    break;

                case "street":
                    addressListPersonPage.enterStreet(Context.getDriver(), row.get(1));
                    break;

                case "contacttype":
                    addressListPersonPage.selectContactType(Context.getDriver(), row.get(1));
                    break;

                case "keywords":
                    addressListPersonPage.enterKeywords(Context.getDriver(), row.get(1));
                    break;
            }
        }
    }

    @When("^I clear the person search filter$")
    public void i_clear_the_person_search_filter() throws Throwable {
        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        addressListPersonPage.clickClearFilter(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the person search filter is empty$")
    public void the_person_search_filter_is_empty() throws Throwable {
        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        Assert.assertTrue("Person search filter is empty",
                addressListPersonPage.isSearchFormEmpty(Context.getDriver()));
    }

    @When("^I want to create a new person$")
    public void i_want_to_create_a_new_person() throws Throwable {
        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        addressListPersonPage.clickCreatePerson(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I list the person search results$")
    public void i_list_the_person_search_results() throws Throwable {
        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        addressListPersonPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the person search results should only contain$")
    public void the_person_search_results_should_contain(DataTable desiredSearchResultTable) throws Throwable {
        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        final List<List<String>> desiredSearchResults = desiredSearchResultTable.raw();
        final List<AddressListPersonItem> searchResults = addressListPersonPage.getSearchResultItems(Context.getDriver(), false);

        // check if we have enough entries
        Assert.assertEquals("Person search results",
                desiredSearchResults.size(), searchResults.size());

        // compare each entries
        for (int i = 0;i < desiredSearchResults.size();++i) {
            final List<String> desiredSearchResult = desiredSearchResults.get(i);
            final AddressListPersonItem searchResult = searchResults.get(i);

            if (!desiredSearchResult.get(1).equals("*"))
                Assert.assertEquals("Name", desiredSearchResult.get(1), searchResult.getName());
            if (!desiredSearchResult.get(2).equals("*"))
                Assert.assertEquals("Company", desiredSearchResult.get(2), searchResult.getCompanyName());
            if (!desiredSearchResult.get(3).equals("*"))
                Assert.assertEquals("Street", desiredSearchResult.get(3), searchResult.getStreet());
            if (!desiredSearchResult.get(4).equals("*"))
                Assert.assertEquals("ZipCode", desiredSearchResult.get(4), searchResult.getZipCode());
            if (!desiredSearchResult.get(5).equals("*"))
                Assert.assertEquals("City", desiredSearchResult.get(5), searchResult.getCity());
            if (!desiredSearchResult.get(6).equals("*"))
                Assert.assertEquals("Phone", desiredSearchResult.get(6), searchResult.getPhone());
        }
    }

    @Then("^the person search results are empty$")
    public void the_person_search_results_are_empty() throws Throwable {
        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        final List<AddressListPersonItem> searchResults = addressListPersonPage.getSearchResultItems(Context.getDriver(), false);

        // check if we have no entries
        Assert.assertEquals("Person search results are empty", 0, searchResults.size());
    }

    @Then("^the freelancer search results should only contain \"([^\"]*)\"$")
    public void the_freelancer_search_results_should_only_contain(String personName) throws Throwable {
        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        final List<AddressListPersonItem> searchResults = addressListPersonPage.getSearchResultItems(Context.getDriver(), true);

        // check if we have enough entries
        Assert.assertEquals("Freelancer search results", 1, searchResults.size());

        final AddressListPersonItem searchResult = searchResults.get(0);
        Assert.assertEquals(personName, searchResult.getName());
    }
}
