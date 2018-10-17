package de.neusta.b4u.steps.jobadvertisement;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.addresses.AddressListPersonItem;
import de.neusta.b4u.binding.addresses.AddressListPersonPage;
import de.neusta.b4u.binding.contact.ContactEditPage;
import de.neusta.b4u.binding.jobadvertisement.JobAdvertisementAddPage;
import de.neusta.b4u.binding.jobadvertisement.JobAdvertisementListItem;
import de.neusta.b4u.binding.jobadvertisement.JobAdvertisementListPage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zih on 4/27/17.
 */
public class JobAdvertisementCreateSteps {
    @When("^I create a new simple job advertisement \"([^\"]*)\"$")
    public void i_create_a_new_simple_job_advertisement(String jobAdvertisementName) throws Throwable {
        ContactEditPage contactEditPage = new ContactEditPage("{id}");

        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        contactEditPage.clickAddJobAdvertisement(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<String> name = Arrays.asList("name", jobAdvertisementName);
        List<List<String>> jobAdvertisementDataRaw = new ArrayList<>();
        jobAdvertisementDataRaw.add(0, name);

        JobAdvertisementAddPage jobAdvertisementAddPage = new JobAdvertisementAddPage();

        Assert.assertTrue("Job advertisement page is displayed",
                jobAdvertisementAddPage.isDisplayed(Context.getDriver()));

        JobAdvertisementStepHelper.applyJobAdvertisementDetails(jobAdvertisementAddPage, jobAdvertisementDataRaw);
        jobAdvertisementAddPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I create a new job advertisement \"([^\"]*)\"$")
    public void i_create_a_new_job_advertisement(String jobAdvertisementName, DataTable jobAdvertisementData) throws Throwable {
        ContactEditPage contactEditPage = new ContactEditPage("{id}");

        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        contactEditPage.clickAddJobAdvertisement(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<String> name = Arrays.asList("name", jobAdvertisementName);
        List<List<String>> jobAdvertisementDataRaw = new ArrayList<>(jobAdvertisementData.raw());
        jobAdvertisementDataRaw.add(0, name);

        JobAdvertisementAddPage jobAdvertisementAddPage = new JobAdvertisementAddPage();

        Assert.assertTrue("Job advertisement page is displayed",
                jobAdvertisementAddPage.isDisplayed(Context.getDriver()));

        JobAdvertisementStepHelper.applyJobAdvertisementDetails(jobAdvertisementAddPage, jobAdvertisementDataRaw);
        jobAdvertisementAddPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^I have checked that the job advertisement \"([^\"]*)\" does not already exist$")
    public void i_have_checked_that_the_job_advertisement_does_not_already_exist(String position) throws Throwable {
        JobAdvertisementListPage jobAdvertisementListPage = new JobAdvertisementListPage();
        jobAdvertisementListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        jobAdvertisementListPage.enterPosition(Context.getDriver(), position);
        jobAdvertisementListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // get search results
        List<JobAdvertisementListItem> searchResults =
                jobAdvertisementListPage.getSearchResultItems(Context.getDriver());
        for (JobAdvertisementListItem searchResult : searchResults) {
            if (searchResult.getPosition().equals(position)) {
                // delete the item
                searchResult.clickRemove();

                SeleniumHelper.acceptAlert(Context.getDriver());
                SeleniumHelper.waitForAngularFinished(Context.getDriver());
                break;
            }
        }
    }

    @When("^I try to create a new job advertisement")
    public void i_try_to_create_a_new_job_advertisement() throws Throwable {
        ContactEditPage contactEditPage = new ContactEditPage("{id}");

        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        contactEditPage.clickAddJobAdvertisement(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the job advertisement \"([^\"]*)\" is created$")
    public void the_job_advertisement_is_created(String position) throws Throwable {
        JobAdvertisementListPage jobAdvertisementListPage = new JobAdvertisementListPage();
        jobAdvertisementListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        jobAdvertisementListPage.enterPosition(Context.getDriver(), position);
        jobAdvertisementListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<JobAdvertisementListItem> jobAdvertisementListItems =
                jobAdvertisementListPage.getSearchResultItems(Context.getDriver());
        Assert.assertFalse("Job advertisement is created", jobAdvertisementListItems.isEmpty());
    }

    @When("^I delete the job advertisement \"([^\"]*)\"$")
    public void i_delete_the_job_advertisement(String position) throws Throwable {
        JobAdvertisementListPage jobAdvertisementListPage = new JobAdvertisementListPage();
        jobAdvertisementListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        jobAdvertisementListPage.enterPosition(Context.getDriver(), position);
        jobAdvertisementListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<JobAdvertisementListItem> jobAdvertisementListItems =
                jobAdvertisementListPage.getSearchResultItems(Context.getDriver());
        for (JobAdvertisementListItem jobAdvertisementListItem : jobAdvertisementListItems) {
            if (jobAdvertisementListItem.getPosition().equals(position)) {
                jobAdvertisementListItem.clickRemove();

                SeleniumHelper.acceptAlert(Context.getDriver());
                SeleniumHelper.waitForAngularFinished(Context.getDriver());

                break;
            }
        }
    }

    @Then("^the job advertisement \"([^\"]*)\" is deleted$")
    public void the_job_advertisement_is_deleted(String position) throws Throwable {
        JobAdvertisementListPage jobAdvertisementListPage = new JobAdvertisementListPage();
        jobAdvertisementListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        jobAdvertisementListPage.clickClearFilter(Context.getDriver());
        jobAdvertisementListPage.enterPosition(Context.getDriver(), position);
        jobAdvertisementListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<JobAdvertisementListItem> jobAdvertisementListItems =
                jobAdvertisementListPage.getSearchResultItems(Context.getDriver());
        Assert.assertEquals("Job advertisement is deleted", 0, jobAdvertisementListItems.size());
    }

    @Then("^the job advertisement popup displays the note \"([^\"]*)\"$")
    public void the_job_advertisement_popup_displays_the_note(String text) throws Throwable {
        JobAdvertisementAddPage jobAdvertisementAddPage = new JobAdvertisementAddPage();

        Assert.assertTrue("Job advertisement page is displayed",
                jobAdvertisementAddPage.isDisplayed(Context.getDriver()));

        Assert.assertEquals(text, jobAdvertisementAddPage.getNote(Context.getDriver()));
    }
}
