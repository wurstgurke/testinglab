package de.neusta.b4u.steps.contact;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.contact.*;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;

import java.util.List;

/**
 * Created by zih on 5/8/17.
 */
public class ContactEditSteps {
    private ContactEditPage contactEditPage = new ContactEditPage("{id}");

    @Then("^the contact details are$")
    public void the_contact_details_are(DataTable contactData) throws Throwable {
        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        for (List<String> row : contactData.raw()) {
            switch (row.get(0)) {
                case "name":
                    Assert.assertEquals("Name",
                            row.get(1), contactEditPage.getName(Context.getDriver()));
                    break;

                case "forperson":
                    Assert.assertEquals("ForPerson",
                            row.get(1), contactEditPage.getForPerson(Context.getDriver()));
                    break;

                case "withperson":
                    Assert.assertEquals("WithPerson",
                            row.get(1), contactEditPage.getWithPerson(Context.getDriver()));
                    break;

                case "withcompany":
                    Assert.assertEquals("WithCompany",
                            row.get(1), contactEditPage.getWithCompany(Context.getDriver()));
                    break;
            }
        }
    }

    @And("^I am on the contact edit page of \"([^\"]*)\"$")
    public void i_am_on_the_contact_edit_page_of(String contactTitle) throws Throwable {
        ContactListPage contactListPage = new ContactListPage();
        contactListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Contact list page is displayed",
                contactListPage.isDisplayed(Context.getDriver()));

        contactListPage.clickClearFilter(Context.getDriver());
        contactListPage.enterTitle(Context.getDriver(), contactTitle);
        contactListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // open the contact page
        List<ContactListItem> contactItems = contactListPage.getSearchResultItems(Context.getDriver());
        contactItems.get(0).clickEdit();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I edit the contact details$")
    public void i_edit_the_contact_details(DataTable contactData) throws Throwable {
        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        contactEditPage.clickEdit(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // apply new contact data
        ContactStepHelper.applyContactDetails(contactEditPage, contactData.raw());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // save the new contact data
        contactEditPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I add a new note to the contact \"([^\"]*)\"$")
    public void i_add_a_new_note_to_the_contact(String note) throws Throwable {
        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        contactEditPage.clickNewNote(Context.getDriver());
        contactEditPage.enterNewNote(Context.getDriver(), note);
        contactEditPage.clickSaveNote(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the contact history should only contain$")
    public void the_contact_history_should_contain(DataTable refContactHistoryTable) throws Throwable {
        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        final List<List<String>> refContactHistoryList = refContactHistoryTable.raw();
        final List<ContactHistoryItem> contactHistoryList = contactEditPage.getContactHistoryList(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Contact history list", refContactHistoryList.size(), contactHistoryList.size());

        // compare each entries
        for (int i = 0;i < refContactHistoryList.size();++i) {
            final List<String> refContactHistoryItem = refContactHistoryList.get(i);
            final ContactHistoryItem contactHistoryItem = contactHistoryList.get(i);

            Assert.assertEquals("Title", refContactHistoryItem.get(0), contactHistoryItem.getTitle());
            Assert.assertEquals("Text", refContactHistoryItem.get(1), contactHistoryItem.getText());
        }
    }

    @When("^I select the contact note \"([^\"]*)\"$")
    public void i_select_the_contact_note(String text) throws Throwable {
        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        ContactHistoryItem historyItem =
                contactEditPage.getContactHistoryItem(Context.getDriver(), text);
        Assert.assertNotNull(historyItem);

        historyItem.select();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }
}
