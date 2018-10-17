package de.neusta.b4u.binding.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zih on 4/26/17.
 */
public class ContactEditPage extends ContactCreatePage {
    public ContactEditPage(String contactId) {
        super("#/contact/edit/" + contactId);
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().contains("#/contact/edit/");
    }

    public String getName(WebDriver driver) {
        return driver.findElement(By.id("contactView.name")).getAttribute("value");
    }

    public String getForPerson(WebDriver driver) {
        return driver.findElement(By.id("contactView.selectedForPerson.link")).getText();
    }

    public String getWithPerson(WebDriver driver) {
        return driver.findElement(By.id("contactView.selectedWithPerson.link")).getText();
    }

    public String getWithCompany(WebDriver driver) {
        return driver.findElement(By.id("contactView.selectedWithPersonCompany.link")).getText();
    }

    public void clickRemove(WebDriver driver) {
        driver.findElement(By.id("contactView.remove")).click();
    }

    public void clickEdit(WebDriver driver) {
        driver.findElement(By.id("contactView.edit")).click();
    }

    public void clickAddTask(WebDriver driver) {
        driver.findElement(By.id("contactTaskView.addTask")).click();
    }

    public void clickAddJobAdvertisement(WebDriver driver) {
        // FIXME: dirty hack to get the create button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("jobAdvertisementsView.create")).click();
    }

    public List<ContactTaskItem> getContactTasks(WebDriver driver) {
        WebElement contactTableView = driver.findElement(By.id("contact-task-table"));
        List<ContactTaskItem> taskList = new ArrayList<>();

        // get all address table entries
        List<WebElement> contactTaskRows = contactTableView.findElements(By.cssSelector("tbody>tr"));
        for (int i = 0; i < contactTaskRows.size();++i) {
            ContactTaskItem contactTaskItem = new ContactTaskItem(i, contactTaskRows.get(i));
            taskList.add(contactTaskItem);
        }

        return taskList;
    }

    public ContactTaskItem getContactTask(WebDriver driver, String taskTitle) {
        List<ContactTaskItem> contactTasks = getContactTasks(driver);
        for (ContactTaskItem contactTask : contactTasks) {
            if (contactTask.getNote().equals(taskTitle)) {
                return contactTask;
            }
        }

        return null;
    }

    public List<ContactHistoryItem> getContactHistoryList(WebDriver driver) {
        WebElement contactHistoryElement = driver.findElement(By.className("history-body"));
        List<ContactHistoryItem> historyList = new ArrayList<>();

        // get all address table entries
        List<WebElement> contactHistoryItems = contactHistoryElement.findElements(By.className("history-item"));
        for (int i = 0; i < contactHistoryItems.size();++i) {
            ContactHistoryItem contactHistoryItem = new ContactHistoryItem(i, contactHistoryItems.get(i));
            historyList.add(contactHistoryItem);
        }

        return historyList;
    }

    public ContactHistoryItem getContactHistoryItem(WebDriver driver, String text) {
        List<ContactHistoryItem> historyList = getContactHistoryList(driver);
        for (ContactHistoryItem historyItem : historyList) {
            if (historyItem.getText().equals(text)) {
                return historyItem;
            }
        }

        return null;
    }
}
