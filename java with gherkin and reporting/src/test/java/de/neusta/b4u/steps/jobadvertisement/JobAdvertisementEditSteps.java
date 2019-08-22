package de.neusta.b4u.steps.jobadvertisement;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.jobadvertisement.*;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;

import java.util.List;

/**
 * Created by zih on 5/10/17.
 */
public class JobAdvertisementEditSteps {
    @And("^the job advertisement details are$")
    public void the_job_advertisement_details_are(DataTable jobAdvertisementDetails) throws Throwable {
        JobAdvertisementEditPage jobAdvertisementEditPage = new JobAdvertisementEditPage("{id}");

        Assert.assertTrue("Job advertisement edit page is displayed",
                jobAdvertisementEditPage.isDisplayed(Context.getDriver()));

        for (List<String> row : jobAdvertisementDetails.raw()) {
            switch (row.get(0)) {
                case "name":
                    Assert.assertEquals("Name",
                            row.get(1), jobAdvertisementEditPage.getName(Context.getDriver()));
                    break;

                case "forperson":
                    Assert.assertEquals("ForPerson",
                            row.get(1), jobAdvertisementEditPage.getForPerson(Context.getDriver()));
                    break;

                case "position":
                    Assert.assertEquals("Position",
                            row.get(1), jobAdvertisementEditPage.getPosition(Context.getDriver()));
                    break;

                case "shortdescription":
                    Assert.assertEquals("ShortDescription",
                            row.get(1), jobAdvertisementEditPage.getShortDescription(Context.getDriver()));
                    break;

                case "taskdescription":
                    Assert.assertEquals("TaskDescription",
                            row.get(1), jobAdvertisementEditPage.getTaskDescription(Context.getDriver()));
                    break;

                case "skills":
                    Assert.assertEquals("Skills",
                            row.get(1), jobAdvertisementEditPage.getSkills(Context.getDriver()));
                    break;

                case "notes":
                    Assert.assertEquals("Notes",
                            row.get(1), jobAdvertisementEditPage.getNotes(Context.getDriver()));
                    break;

                case "keywords":
                    Assert.assertEquals("Keywords",
                            row.get(1), jobAdvertisementEditPage.getKeywords(Context.getDriver()));
                    break;

                case "zipcode":
                    Assert.assertEquals("ZipCode",
                            row.get(1), jobAdvertisementEditPage.getZipCode(Context.getDriver()));
                    break;

                case "city":
                    Assert.assertEquals("City",
                            row.get(1), jobAdvertisementEditPage.getCity(Context.getDriver()));
                    break;

                case "country":
                    Assert.assertEquals("Country",
                            row.get(1), jobAdvertisementEditPage.getCountry(Context.getDriver()));
                    break;

                case "federalstate":
                    Assert.assertEquals("FederalState",
                            row.get(1), jobAdvertisementEditPage.getFederalState(Context.getDriver()));
                    break;

                case "location":
                    Assert.assertEquals("Location",
                            row.get(1), jobAdvertisementEditPage.getLocation(Context.getDriver()));
                    break;

                case "startdate":
                    Assert.assertEquals("StartDate",
                            row.get(1), jobAdvertisementEditPage.getStartDate(Context.getDriver()));
                    break;

                case "enddate":
                    Assert.assertEquals("EndDate",
                            row.get(1), jobAdvertisementEditPage.getEndDate(Context.getDriver()));
                    break;

                case "duration":
                    Assert.assertEquals("Duration",
                            row.get(1), jobAdvertisementEditPage.getDuration(Context.getDriver()));
                    break;
            }
        }
    }

    @And("^I am on the job advertisement edit page of \"([^\"]*)\"$")
    public void i_am_on_the_job_advertisement_edit_page_of(String position) throws Throwable {
        JobAdvertisementListPage jobAdvertisementListPage = new JobAdvertisementListPage();
        jobAdvertisementListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        jobAdvertisementListPage.clickClearFilter(Context.getDriver());
        jobAdvertisementListPage.enterPosition(Context.getDriver(), position);
        jobAdvertisementListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // open the job advertisement page
        List<JobAdvertisementListItem> jobAdvertisementListItems =
                jobAdvertisementListPage.getSearchResultItems(Context.getDriver());

        Assert.assertFalse("Job advertisement list is not empty",
                jobAdvertisementListItems.isEmpty());

        jobAdvertisementListItems.get(0).clickView();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I edit the job advertisement details$")
    public void i_edit_the_job_advertisement_details(DataTable jobAdvertisementDetails) throws Throwable {
        JobAdvertisementEditPage jobAdvertisementEditPage = new JobAdvertisementEditPage("{id}");

        Assert.assertTrue("Job advertisement edit page is displayed",
                jobAdvertisementEditPage.isDisplayed(Context.getDriver()));

        jobAdvertisementEditPage.clickEdit(Context.getDriver());
        JobAdvertisementStepHelper.applyJobAdvertisementDetails(jobAdvertisementEditPage, jobAdvertisementDetails.raw());
        jobAdvertisementEditPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^I add the applicant \"([^\"]*)\" to the job advertisement$")
    public void i_add_the_applicant_to_the_job_advertisement(String jobApplicant) throws Throwable {
        JobAdvertisementEditPage jobAdvertisementEditPage = new JobAdvertisementEditPage("{id}");

        Assert.assertTrue("Job advertisement edit page is displayed",
                jobAdvertisementEditPage.isDisplayed(Context.getDriver()));

        jobAdvertisementEditPage.openAddApplicantList(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        AddApplicantPopup addApplicantPopup = new AddApplicantPopup();
        addApplicantPopup.enterName(Context.getDriver(), jobApplicant);
        addApplicantPopup.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // get first employee item
        List<AddApplicantPopupItem> applicantList =
                addApplicantPopup.getApplicantList(Context.getDriver());
        Assert.assertTrue("Applicant list is not empty", !applicantList.isEmpty());

        applicantList.get(0).clickAddToJobAdvertisement();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^the job advertisement applicant list contains$")
    public void the_job_advertisement_applicant_list_contains(DataTable expectedApplicantListTable) throws Throwable {
        JobAdvertisementEditPage jobAdvertisementEditPage = new JobAdvertisementEditPage("{id}");

        Assert.assertTrue("Job advertisement edit page is displayed",
                jobAdvertisementEditPage.isDisplayed(Context.getDriver()));

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        final List<List<String>> expectedApplicantList = expectedApplicantListTable.raw();
        List<JobAdvertisementApplicantItem> applicantList =
                jobAdvertisementEditPage.getApplicantList(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Job advertisement applicant list",
                expectedApplicantList.size(), applicantList.size());

        // compare each entries
        for (int i = 0;i < expectedApplicantList.size();++i) {
            final List<String> desiredApplicantItem = expectedApplicantList.get(i);
            final JobAdvertisementApplicantItem applicantItem = applicantList.get(i);

            Assert.assertEquals("FirstName", desiredApplicantItem.get(0), applicantItem.getFirstName());
            Assert.assertEquals("LastName", desiredApplicantItem.get(1), applicantItem.getLastName());
            Assert.assertEquals("CompanyName", desiredApplicantItem.get(2), applicantItem.getCompanyName());
        }
    }
}
