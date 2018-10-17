package de.neusta.b4u.steps.tasks;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.contact.ContactEditPage;
import de.neusta.b4u.binding.contact.ContactTaskItem;
import de.neusta.b4u.binding.tasks.TaskPopupPage;
import de.neusta.b4u.binding.tasks.TaskListItem;
import de.neusta.b4u.binding.tasks.TaskListPage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zih on 4/27/17.
 */
public class TaskCreateSteps {
    @When("^I create a new task \"([^\"]*)\"$")
    public void i_create_a_new_task(String taskTitle, DataTable taskData) throws Throwable {
        ContactEditPage contactEditPage = new ContactEditPage("{id}");

        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        contactEditPage.clickAddTask(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<String> name = Arrays.asList("note", taskTitle);
        List<List<String>> taskDataRaw = new ArrayList<>(taskData.raw());
        taskDataRaw.add(0, name);

        TaskPopupPage addTaskPage = new TaskPopupPage();

        Assert.assertTrue("Task popup is displayed",
                addTaskPage.isDisplayed(Context.getDriver()));

        TaskStepHelper.applyTaskDetails(addTaskPage, taskDataRaw);
        addTaskPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

     @Then("^the task \"([^\"]*)\" is created$")
    public void the_task_is_created(String taskTitle) throws Throwable {
        TaskListPage taskListPage = new TaskListPage();
        taskListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Task list page is displayed",
                taskListPage.isDisplayed(Context.getDriver()));

        taskListPage.enterNote(Context.getDriver(), taskTitle);
        taskListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // open the contact page
        List<TaskListItem> taskItems = taskListPage.getSearchResultItems(Context.getDriver());
        Assert.assertFalse("Task is created", taskItems.isEmpty());
    }

    @When("^I delete the task \"([^\"]*)\"$")
    public void i_delete_the_task(String taskTitle) throws Throwable {
        ContactEditPage contactEditPage = new ContactEditPage("{id}");

        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        ContactTaskItem contactTask = contactEditPage.getContactTask(Context.getDriver(), taskTitle);

        if (contactTask != null) {
            contactTask.clickRemove();

            SeleniumHelper.acceptAlert(Context.getDriver());
            SeleniumHelper.waitForAngularFinished(Context.getDriver());
        }
    }

    @Then("^the task \"([^\"]*)\" is deleted$")
    public void the_task_is_deleted(String taskTitle) throws Throwable {
        TaskListPage taskListPage = new TaskListPage();
        taskListPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Task list page is displayed",
                taskListPage.isDisplayed(Context.getDriver()));

        taskListPage.enterNote(Context.getDriver(), taskTitle);
        taskListPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // open the contact page
        List<TaskListItem> taskItems = taskListPage.getSearchResultItems(Context.getDriver());
        Assert.assertTrue("Task is deleted", taskItems.isEmpty());
    }
}
