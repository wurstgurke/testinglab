package de.neusta.b4u.steps.contact;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.contact.ContactCreatePage;
import de.neusta.b4u.binding.contact.ContactEditPage;
import de.neusta.b4u.binding.contact.ContactListItem;
import de.neusta.b4u.binding.contact.ContactListPage;
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
public class ContactCreateSteps {
    private ContactCreatePage createContactPage = new ContactCreatePage();

    @Then("^the create contact page is being displayed$")
    public void the_create_contact_page_is_being_displayed() throws Throwable {
        Assert.assertTrue("Contact create page is displayed",
                createContactPage.isDisplayed(Context.getDriver()));
    }

    @When("^I am on the create new contact page$")
    public void i_am_on_the_create_new_contact_page() throws Throwable {
        createContactPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Given("^I have checked that the contact \"([^\"]*)\" does not already exist$")
    public void i_have_checked_that_the_contact_does_not_already_exist(String contactTitle) throws Throwable {
        ContactListPage contactListPage = new ContactListPage();
        contactListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        contactListPage.selectSelectedForPerson(Context.getDriver(), "- alle -");
        contactListPage.enterTitle(Context.getDriver(), contactTitle);
        contactListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // get search results
        List<ContactListItem> searchResults = contactListPage.getSearchResultItems(Context.getDriver());
        for (ContactListItem searchResult : searchResults) {
            if (searchResult.getTitle().equals(contactTitle)) {
                // delete the item
                searchResult.clickRemove();

                SeleniumHelper.acceptAlert(Context.getDriver());
                SeleniumHelper.waitForAngularFinished(Context.getDriver());

                break;
            }
        }
    }

    @When("^I create a new simple contact \"([^\"]*)\"$")
    public void i_create_a_new_simple_contact(String contactName) throws Throwable {
        createContactPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Contact create page is displayed",
                createContactPage.isDisplayed(Context.getDriver()));

        List<String> name = Arrays.asList("name", contactName);
        List<List<String>> contactDataRaw = new ArrayList<>();
        contactDataRaw.add(0, name);

        ContactStepHelper.applyContactDetails(createContactPage, contactDataRaw);

        createContactPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I create a new contact \"([^\"]*)\"$")
    public void i_create_a_new_contact(String contactName, DataTable contactData) throws Throwable {
        createContactPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Contact create page is displayed",
                createContactPage.isDisplayed(Context.getDriver()));

        List<String> name = Arrays.asList("name", contactName);
        List<List<String>> contactDataRaw = new ArrayList<>(contactData.raw());
        contactDataRaw.add(0, name);

        ContactStepHelper.applyContactDetails(createContactPage, contactDataRaw);

        createContactPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the contact \"([^\"]*)\" is created$")
    public void the_contact_is_created(String contactTitle) throws Throwable {
        ContactListPage contactListPage = new ContactListPage();
        contactListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        contactListPage.selectSelectedForPerson(Context.getDriver(), "- alle -");
        contactListPage.enterTitle(Context.getDriver(), contactTitle);
        contactListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<ContactListItem> contactItems = contactListPage.getSearchResultItems(Context.getDriver());
        Assert.assertFalse("Contact is created", contactItems.isEmpty());
    }

    @Given("^I have ensured that the contact \"([^\"]*)\" already exist$")
    public void i_have_ensured_that_the_contact_i_want_to_edit_already_exist(String contactTitle) throws Throwable {
        ContactListPage contactListPage = new ContactListPage();
        contactListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        contactListPage.selectSelectedForPerson(Context.getDriver(), "- alle -");
        contactListPage.enterTitle(Context.getDriver(), contactTitle);
        contactListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<ContactListItem> contactItems = contactListPage.getSearchResultItems(Context.getDriver());
        if (contactItems.isEmpty()) {
            createContactPage.open(Context.getDriver());

            SeleniumHelper.waitForAngularFinished(Context.getDriver());

            Assert.assertTrue("Contact create page is displayed",
                    createContactPage.isDisplayed(Context.getDriver()));

            createContactPage.enterName(Context.getDriver(), contactTitle);
            createContactPage.clickSave(Context.getDriver());

            SeleniumHelper.waitForAngularFinished(Context.getDriver());
        }
    }
}
