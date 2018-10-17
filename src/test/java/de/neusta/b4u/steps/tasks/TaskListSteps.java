package de.neusta.b4u.steps.tasks;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.tasks.TaskListItem;
import de.neusta.b4u.binding.tasks.TaskListPage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;

import java.util.List;

/**
 * Created by zih on 4/27/17.
 */
public class TaskListSteps {
    private TaskListPage taskListPage = new TaskListPage();

    @And("^I am on the task list page$")
    public void i_am_on_the_task_list_page() throws Throwable {
        taskListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^I provide the following data to the task search filter$")
    public void i_provide_the_following_data_to_the_task_search_filter(DataTable searchFilter) throws Throwable {
        Assert.assertTrue("Task list page is displayed",
                taskListPage.isDisplayed(Context.getDriver()));

        for (List<String> row : searchFilter.raw()) {
            switch (row.get(0)) {
                case "taskperson":
                    taskListPage.selectTaskPerson(Context.getDriver(), row.get(1));
                    break;

                case "tasktype":
                    taskListPage.selectTaskType(Context.getDriver(), row.get(1));
                    break;

                case "taskstatus":
                    taskListPage.selectTaskStatus(Context.getDriver(), row.get(1));
                    break;

                case "note":
                    taskListPage.enterNote(Context.getDriver(), row.get(1));
                    break;

                case "fromdate":
                    taskListPage.enterFromDate(Context.getDriver(), row.get(1));
                    break;

                case "todate":
                    taskListPage.enterToDate(Context.getDriver(), row.get(1));
                    break;
            }
        }
    }

    @When("^I clear the task search filter$")
    public void i_clear_the_task_search_filter() throws Throwable {
        Assert.assertTrue("Task list page is displayed",
                taskListPage.isDisplayed(Context.getDriver()));

        taskListPage.clickClearFilter(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the task search filter is empty$")
    public void the_task_search_filter_is_empty() throws Throwable {
        Assert.assertTrue("Task list page is displayed",
                taskListPage.isDisplayed(Context.getDriver()));

        Assert.assertTrue("Task search filter is empty",
                taskListPage.isSearchFormEmpty(Context.getDriver()));
    }

    @When("^I list the task search results$")
    public void i_list_the_task_search_results() throws Throwable {
        Assert.assertTrue("Task list page is displayed",
                taskListPage.isDisplayed(Context.getDriver()));

        taskListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the task search results should only contain$")
    public void the_task_search_results_should_only_contain(DataTable desiredSearchResultTable) throws Throwable {
        Assert.assertTrue("Task list page is displayed",
                taskListPage.isDisplayed(Context.getDriver()));

        final List<List<String>> desiredSearchResults = desiredSearchResultTable.raw();
        final List<TaskListItem> searchResults = taskListPage.getSearchResultItems(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Task search results", desiredSearchResults.size(), searchResults.size());

        // compare each entries
        for (int i = 0;i < desiredSearchResults.size();++i) {
            final List<String> desiredSearchResult = desiredSearchResults.get(i);
            final TaskListItem searchResult = searchResults.get(i);

            Assert.assertEquals("Title", desiredSearchResult.get(0), searchResult.getTitle());
            Assert.assertEquals("ForPerson", desiredSearchResult.get(1), searchResult.getForPerson());
            Assert.assertEquals("StartDate", desiredSearchResult.get(2), searchResult.getStartDate());
            Assert.assertEquals("EndDate", desiredSearchResult.get(3), searchResult.getEndDate());
            Assert.assertEquals("Type", desiredSearchResult.get(4), searchResult.getType());
            Assert.assertEquals("Reminder", desiredSearchResult.get(5), searchResult.getReminder());
        }
    }

    @Then("^the task search results are empty$")
    public void the_task_search_results_are_empty() throws Throwable {
        Assert.assertTrue("Task list page is displayed",
                taskListPage.isDisplayed(Context.getDriver()));

        final List<TaskListItem> searchResults = taskListPage.getSearchResultItems(Context.getDriver());

        // check if we have no entries
        Assert.assertEquals("Task search results are empty", 0, searchResults.size());
    }
}
