package de.neusta.b4u.binding.tasks;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.binding.contact.ContactListItem;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zih on 4/6/17.
 */
public class TaskListPage extends BasePage {
    public TaskListPage() {
        super("#/task/list");
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().endsWith("#/task/list");
    }

    public void selectTaskPerson(WebDriver driver, String taskPerson) {
        SeleniumHelper.selectFromDropdown(driver, By.id("taskTableView.taskPersonList"), taskPerson);
    }

    public void selectTaskType(WebDriver driver, String taskType) {
        SeleniumHelper.selectFromDropdown(driver, By.id("taskTableView.taskTypeList"), taskType);
    }

    public void selectTaskStatus(WebDriver driver, String taskStatus) {
        SeleniumHelper.selectFromDropdown(driver, By.id("taskTableView.taskStatusList"), taskStatus);
    }

    public void enterNote(WebDriver driver, String title) {
        driver.findElement(By.id("taskTableView.title")).sendKeys(title);
    }

    public void enterFromDate(WebDriver driver, String fromDate) {
        driver.findElement(By.id("taskTableView.fromDate.input")).sendKeys(fromDate);
    }

    public void enterToDate(WebDriver driver, String toDate) {
        driver.findElement(By.id("taskTableView.toDate.input")).sendKeys(toDate);
    }

    public void clickSearch(WebDriver driver) {
        driver.findElement(By.id("taskTableView.search1")).click();
        //driver.findElement(By.id("taskTableView.search2")).click();
    }

    public void clickClearFilter(WebDriver driver) {
        driver.findElement(By.id("taskTableView.clear")).click();
    }

    public List<TaskListItem> getSearchResultItems(WebDriver driver) {
        WebElement taskListTable = driver.findElement(By.id("taskTableView.table"));

        List<TaskListItem> searchResults = new ArrayList<>();

        // get all address table entries
        List<WebElement> contactTableRows = taskListTable.findElements(By.cssSelector("tbody>tr"));

        for (int i = 0; i < contactTableRows.size();++i) {
            TaskListItem taskListItem = new TaskListItem(i, contactTableRows.get(i));
            searchResults.add(taskListItem);
        }

        return searchResults;
    }

    public boolean isSearchFormEmpty(WebDriver driver) {
        return driver.findElement(By.id("taskTableView.title")).getText().isEmpty() &&
                driver.findElement(By.id("taskTableView.fromDate.input")).getText().isEmpty() &&
                driver.findElement(By.id("taskTableView.toDate.input")).getText().isEmpty();
    }
}
