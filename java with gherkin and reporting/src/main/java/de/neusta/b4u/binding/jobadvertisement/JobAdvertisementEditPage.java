package de.neusta.b4u.binding.jobadvertisement;

import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zih on 5/10/17.
 */
public class JobAdvertisementEditPage extends JobAdvertisementAddPage {
    public JobAdvertisementEditPage(String jobAdvertisementId) {
        super("#/jobAdvertisement/" + jobAdvertisementId);
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().contains("#/jobAdvertisement/");
    }

    public void enterName(WebDriver driver, String name) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.name"));
        element.clear();
        element.sendKeys(name);
    }

    public String getName(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.name")).getAttribute("value");
    }

    public void selectForPerson(WebDriver driver, String forPerson) {
        SeleniumHelper.selectFromDropdown(driver, By.id("jobAdvertisementDirectiveView.selectedForPerson"), forPerson);
    }

    public String getForPerson(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.nameText")).getText();
    }

    public void enterPosition(WebDriver driver, String position) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.position"));
        element.clear();
        element.sendKeys(position);
    }

    public String getPosition(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.position")).getAttribute("value");
    }

    public void enterShortDescription(WebDriver driver, String shortDescription) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.shortDescription"));
        element.clear();
        element.sendKeys(shortDescription);
    }

    public String getShortDescription(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.shortDescription")).getAttribute("value");
    }

    public void enterTaskDescription(WebDriver driver, String taskDescription) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.taskDescription"));
        element.clear();
        element.sendKeys(taskDescription);
    }

    public String getTaskDescription(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.taskDescription")).getAttribute("value");
    }

    public void enterSkills(WebDriver driver, String skills) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.skills"));
        element.clear();
        element.sendKeys(skills);
    }

    public String getSkills(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.skills")).getAttribute("value");
    }

    public void enterNotes(WebDriver driver, String notes) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.notes"));
        element.clear();
        element.sendKeys(notes);
    }

    public String getNotes(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.notes")).getAttribute("value");
    }

    public void enterKeywords(WebDriver driver, String keywords) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.keywords"));
        element.clear();
        element.sendKeys(keywords);
    }

    public String getKeywords(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.keywords")).getAttribute("value");
    }

    public void enterZipCode(WebDriver driver, String zipCode) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.zipCode"));
        element.clear();
        element.sendKeys(zipCode);
    }

    public String getZipCode(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.zipCode")).getAttribute("value");
    }

    public void enterCity(WebDriver driver, String city) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.city"));
        element.clear();
        element.sendKeys(city);
    }

    public String getCity(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.city")).getAttribute("value");
    }

    public void selectCountry(WebDriver driver, String country) {
        SeleniumHelper.selectFromDropdown(driver, By.id("jobAdvertisementDirectiveView.country"), country);
    }

    public String getCountry(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.countryText")).getText();
    }

    public void selectFederalState(WebDriver driver, String federalState) {
        SeleniumHelper.selectFromDropdown(driver, By.id("jobAdvertisementDirectiveView.federalState"), federalState);
    }

    public String getFederalState(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.federalStateText")).getText();
    }

    public void enterLocation(WebDriver driver, String location) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.location"));
        element.clear();
        element.sendKeys(location);
    }

    public String getLocation(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.location")).getAttribute("value");
    }

    public void enterStartDate(WebDriver driver, String startDate) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.startDate.input"));
        element.clear();
        element.sendKeys(startDate);
    }

    public String getStartDate(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.startDate")).getText();
    }

    public void enterEndDate(WebDriver driver, String endDate) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.endDate.input"));
        element.clear();
        element.sendKeys(endDate);
    }

    public String getEndDate(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.endDate")).getText();
    }

    public void enterDuration(WebDriver driver, String duration) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDirectiveView.duration"));
        element.clear();
        element.sendKeys(duration);
    }

    public String getDuration(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementDirectiveView.duration")).getAttribute("value");
    }

    public void clickEdit(WebDriver driver) {
        // FIXME: dirty hack to get the edit button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("jobAdvertisementDirectiveView.edit")).click();
    }

    public void clickSave(WebDriver driver) {
        // FIXME: dirty hack to get the save button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("jobAdvertisementDirectiveView.endEdit1")).click();
//        driver.findElement(By.id("jobAdvertisementDirectiveView.endEdit2")).click();
    }

    public void openAddApplicantList(WebDriver driver) {
        // FIXME: dirty hack to get the save button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("applicantPersonListView.add")).click();
    }

    public List<JobAdvertisementApplicantItem> getApplicantList(WebDriver driver) {
        WebElement employeeListTableView = driver.findElement(By.id("applicanPersonListView.table"));

        List<JobAdvertisementApplicantItem> applicantList = new ArrayList<>();

        List<WebElement> employeeListTableRows = employeeListTableView.findElements(By.cssSelector("tbody>tr"));
        for (int i = 0; i < employeeListTableRows.size();++i) {
            JobAdvertisementApplicantItem applicantItem =
                    new JobAdvertisementApplicantItem(i, employeeListTableRows.get(i));

            applicantList.add(applicantItem);
        }

        return applicantList;
    }
}
