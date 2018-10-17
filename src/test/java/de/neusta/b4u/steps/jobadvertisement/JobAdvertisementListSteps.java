package de.neusta.b4u.steps.jobadvertisement;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.jobadvertisement.JobAdvertisementListItem;
import de.neusta.b4u.binding.jobadvertisement.JobAdvertisementListPage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;

import java.util.List;

/**
 * Created by zih on 5/4/17.
 */
public class JobAdvertisementListSteps {
    private JobAdvertisementListPage jobAdvertisementListPage = new JobAdvertisementListPage();

    @And("^I am on the job advertisement list page$")
    public void i_am_on_the_task_list_page() throws Throwable {
        jobAdvertisementListPage.open(Context.getDriver());
    }

    @And("^I provide the following data to the job advertisement search filter$")
    public void i_provide_the_following_data_to_the_job_advertisement_search_filter(DataTable searchFilter) throws Throwable {
        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        for (List<String> row : searchFilter.raw()) {
            switch (row.get(0)) {
                case "id":
                    jobAdvertisementListPage.enterId(Context.getDriver(), row.get(1));
                    break;

                case "forperson":
                    jobAdvertisementListPage.selectForPerson(Context.getDriver(), row.get(1));
                    break;

                case "position":
                    jobAdvertisementListPage.enterPosition(Context.getDriver(), row.get(1));
                    break;

                case "state":
                    jobAdvertisementListPage.selectState(Context.getDriver(), row.get(1));
                    break;

                case "startdate":
                    jobAdvertisementListPage.enterStartDate(Context.getDriver(), row.get(1));
                    break;

                case "enddate":
                    jobAdvertisementListPage.enterEndDate(Context.getDriver(), row.get(1));
                    break;
            }
        }
    }

    @When("^I clear the job advertisement search filter$")
    public void i_clear_the_job_advertisement_search_filter() throws Throwable {
        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        jobAdvertisementListPage.clickClearFilter(Context.getDriver());
    }

    @Then("^the job advertisement filter is empty$")
    public void the_job_advertisement_filter_is_empty() throws Throwable {
        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        Assert.assertTrue("Job advertisement search filter is empty",
                jobAdvertisementListPage.isSearchFormEmpty(Context.getDriver()));
    }

    @When("^I list the job advertisement search results$")
    public void i_list_the_job_advertisement_search_results() throws Throwable {
        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        jobAdvertisementListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the job advertisement search results should only contain$")
    public void the_job_advertisement_search_results_should_only_contain(DataTable desiredSearchResultTable) throws Throwable {
        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        final List<List<String>> desiredSearchResults = desiredSearchResultTable.raw();
        final List<JobAdvertisementListItem> searchResults =
                jobAdvertisementListPage.getSearchResultItems(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Job advertisement search results", desiredSearchResults.size(), searchResults.size());

        // compare each entries
        for (int i = 0;i < desiredSearchResults.size();++i) {
            final List<String> desiredSearchResult = desiredSearchResults.get(i);
            final JobAdvertisementListItem searchResult = searchResults.get(i);

            Assert.assertEquals("CreateDate", desiredSearchResult.get(0), searchResult.getCreateDate());
            Assert.assertEquals("Number", desiredSearchResult.get(1), searchResult.getNumber());
            Assert.assertEquals("Position", desiredSearchResult.get(2), searchResult.getPosition());
            Assert.assertEquals("Company", desiredSearchResult.get(3), searchResult.getCompany());
            Assert.assertEquals("Name", desiredSearchResult.get(4), searchResult.getName());
            Assert.assertEquals("Period", desiredSearchResult.get(5), searchResult.getPeriod());
            Assert.assertEquals("ForPerson", desiredSearchResult.get(6), searchResult.getForPersonName());
            Assert.assertEquals("CreatePerson", desiredSearchResult.get(7), searchResult.getCreatePersonName());
        }
    }

    @Then("^the job advertisement search results are empty$")
    public void the_job_advertisement_search_results_are_empty() throws Throwable {
        Assert.assertTrue("Job advertisement list page is displayed",
                jobAdvertisementListPage.isDisplayed(Context.getDriver()));

        final List<JobAdvertisementListItem> searchResults =
                jobAdvertisementListPage.getSearchResultItems(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Job advertisement search results are empty", 0, searchResults.size());
    }
}
