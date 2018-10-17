package de.neusta.b4u.binding.tasks;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 4/27/17.
 */
public class TaskPopupPage extends BasePage {
    public TaskPopupPage() {
        super("");
    }

    public boolean isDisplayed(WebDriver driver) {
        return SeleniumHelper.elementExists(driver, By.id("edit-task"));
    }

    public void selectTaskType(WebDriver driver, String taskType) {
        SeleniumHelper.selectFromDropdown(driver, By.id("editTaskView.taskTypes"), taskType);
    }

    public void selectReminder(WebDriver driver, String reminder) {
        SeleniumHelper.selectFromDropdown(driver, By.id("editTaskView.taskReminderTypes"), reminder);
    }

    public void enterStartDate(WebDriver driver, String startDate) {
        WebElement element = driver.findElement(By.id("editTaskView.startDate.input"));
        element.clear();
        element.sendKeys(startDate);
    }

    public void enterEndDate(WebDriver driver, String endDate) {
        WebElement element = driver.findElement(By.id("editTaskView.endDate.input"));
        element.clear();
        element.sendKeys(endDate);
    }

    public void enterForPerson(WebDriver driver, String forPerson) {
        WebElement element = driver.findElement(By.id("editTaskView.forPerson"));
        element.clear();
        element.sendKeys(forPerson);
        element.click();
    }

    public void enterNote(WebDriver driver, String note) {
        WebElement element = driver.findElement(By.id("editTaskView.title"));
        element.clear();
        element.sendKeys(note);
        element.click();
    }


    public void clickSave(WebDriver driver) {
        driver.findElement(By.id("editTaskView.save")).click();
    }
}