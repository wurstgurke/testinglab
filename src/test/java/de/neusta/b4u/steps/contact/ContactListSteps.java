package de.neusta.b4u.steps.contact;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.contact.ContactListItem;
import de.neusta.b4u.binding.contact.ContactListPage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;

import java.util.List;

/**
 * Created by zih on 4/20/17.
 */
public class ContactListSteps {
    private ContactListPage contactListPage = new ContactListPage();

    @And("^I am on the contact list page$")
    public void i_am_on_the_contact_address_list_page() throws Throwable {
        contactListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^I provide the following data to the contact search filter$")
    public void i_provide_the_following_data_to_the_contact_search_filter(DataTable searchFilter) throws Throwable {
        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        for (List<String> row : searchFilter.raw()) {
            switch (row.get(0)) {
                case "selectedforperson":
                    contactListPage.selectSelectedForPerson(Context.getDriver(), row.get(1));
                    break;

                case "selectedtasktype":
                    contactListPage.selectSelectedForTaskType(Context.getDriver(), row.get(1));
                    break;

                case "title":
                    contactListPage.enterTitle(Context.getDriver(), row.get(1));
                    break;

                case "selectedwithperson":
                    contactListPage.enterSelectedWithPerson(Context.getDriver(), row.get(1));
                    break;

                case "selectedwithcompany":
                    contactListPage.enterSelectedWithCompany(Context.getDriver(), row.get(1));
                    break;
            }

            Thread.sleep(100);
        }
    }

    @When("^I clear the contact search filter$")
    public void i_clear_the_contact_search_filter() throws Throwable {
        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        contactListPage.clickClearFilter(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the contact search filter is empty$")
    public void the_contact_search_filter_is_empty() throws Throwable {
        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        Assert.assertTrue("Contact search filter is empty",
                contactListPage.isSearchFormEmpty(Context.getDriver()));
    }

    @When("^I want to create a new contact")
    public void i_want_to_create_a_new_contact() throws Throwable {
        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        contactListPage.clickCreateContact(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I list the contact search results$")
    public void i_list_the_contact_search_results() throws Throwable {
        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        contactListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the contact search results should only contain$")
    public void the_contact_search_results_should_contain(DataTable desiredSearchResultTable) throws Throwable {
        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        final List<List<String>> desiredSearchResults = desiredSearchResultTable.raw();
        final List<ContactListItem> searchResults = contactListPage.getSearchResultItems(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Contact search results", desiredSearchResults.size(), searchResults.size());

        // compare each entries
        for (int i = 0;i < desiredSearchResults.size();++i) {
            final List<String> desiredSearchResult = desiredSearchResults.get(i);
            final ContactListItem searchResult = searchResults.get(i);

            Assert.assertEquals("Title", desiredSearchResult.get(0), searchResult.getTitle());
            Assert.assertEquals("ForPerson", desiredSearchResult.get(1), searchResult.getForPerson());
            Assert.assertEquals("WithPerson", desiredSearchResult.get(2), searchResult.getWithPerson());
            Assert.assertEquals("WithCompany", desiredSearchResult.get(3), searchResult.getWithCompany());
        }
    }

    @Then("^the contact search results are empty$")
    public void the_contact_search_results_are_empty() throws Throwable {
        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        final List<ContactListItem> searchResults =
                contactListPage.getSearchResultItems(Context.getDriver());

        // check if we have no entries
        Assert.assertEquals("Contact search results are empty",0, searchResults.size());
    }
}
