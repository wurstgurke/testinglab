package de.neusta.b4u.steps.tasks;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.contact.ContactEditPage;
import de.neusta.b4u.binding.contact.ContactTaskItem;
import de.neusta.b4u.binding.tasks.TaskPopupPage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;

import java.util.List;

/**
 * Created by zih on 5/8/17.
 */
public class TaskEditSteps {
    private ContactEditPage contactEditPage = new ContactEditPage("{id}");

    @Then("^the task details of \"([^\"]*)\" are$")
    public void the_task_details_are(String taskTitle, DataTable taskData) throws Throwable {
        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        ContactTaskItem contactTask = contactEditPage.getContactTask(Context.getDriver(), taskTitle);
        Assert.assertNotNull(contactTask);

        for (List<String> row : taskData.raw()) {
            switch (row.get(0)) {
                case "note":
                    Assert.assertEquals("Title", row.get(1), contactTask.getNote());
                    break;

                case "type":
                    Assert.assertEquals("Type", row.get(1), contactTask.getType());
                    break;

                case "reminder":
                    Assert.assertEquals("Reminder", row.get(1), contactTask.getReminder());
                    break;

                case "startdate":
                    Assert.assertEquals("StartDate", row.get(1), contactTask.getStartDate());
                    break;

                case "enddate":
                    Assert.assertEquals("EndDate", row.get(1), contactTask.getEndDate());
                    break;
            }
        }
    }

    @When("^I edit the task \"([^\"]*)\"$")
    public void i_edit_the_task(String taskTitle, DataTable taskData) throws Throwable {
        Assert.assertTrue("Contact edit page is displayed",
                contactEditPage.isDisplayed(Context.getDriver()));

        ContactTaskItem contactTask = contactEditPage.getContactTask(Context.getDriver(), taskTitle);
        Assert.assertNotNull(contactTask);

        contactTask.clickEdit();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        TaskPopupPage editTaskPage = new TaskPopupPage();

        Assert.assertTrue("Task popup is displayed",
                editTaskPage.isDisplayed(Context.getDriver()));

        TaskStepHelper.applyTaskDetails(editTaskPage, taskData.raw());
        editTaskPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }
}
