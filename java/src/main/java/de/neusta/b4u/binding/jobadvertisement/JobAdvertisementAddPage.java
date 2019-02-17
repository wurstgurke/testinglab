package de.neusta.b4u.binding.jobadvertisement;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 5/4/17.
 */
public class JobAdvertisementAddPage extends BasePage {
    public JobAdvertisementAddPage() {
        super("");
    }

    protected JobAdvertisementAddPage(String path) {
        super(path);
    }

    public boolean isDisplayed(WebDriver driver) {
        return SeleniumHelper.elementExists(driver, By.id("job-advertisement-create"));
    }

    public void enterName(WebDriver driver, String name) {
        driver.findElement(By.id("jobAdvertisementDetailsView.name")).sendKeys(name);
    }

    public void selectForPerson(WebDriver driver, String forPerson) {
        SeleniumHelper.selectFromDropdown(driver, By.id("jobAdvertisementDetailsView.selectedForPerson"), forPerson);
    }

    public void enterPosition(WebDriver driver, String position) {
        driver.findElement(By.id("jobAdvertisementDetailsView.position")).sendKeys(position);
    }

    public void enterShortDescription(WebDriver driver, String shortDescription) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDetailsView.shortDescription"));
        element.clear();
        element.sendKeys(shortDescription);
    }

    public void enterTaskDescription(WebDriver driver, String taskDescription) {
        driver.findElement(By.id("jobAdvertisementDetailsView.taskDescription")).sendKeys(taskDescription);
    }

    public void enterSkills(WebDriver driver, String skills) {
        driver.findElement(By.id("jobAdvertisementDetailsView.skills")).sendKeys(skills);
    }

    public void enterNotes(WebDriver driver, String notes) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDetailsView.notes"));
        element.clear();
        element.sendKeys(notes);
    }

    public void enterKeywords(WebDriver driver, String keywords) {
        driver.findElement(By.id("jobAdvertisementDetailsView.keywords")).sendKeys(keywords);
    }

    public void enterZipCode(WebDriver driver, String zipCode) {
        driver.findElement(By.id("jobAdvertisementDetailsView.zipCode")).sendKeys(zipCode);
    }

    public void enterCity(WebDriver driver, String city) {
        driver.findElement(By.id("jobAdvertisementDetailsView.city")).sendKeys(city);
    }

    public void selectCountry(WebDriver driver, String country) {
        SeleniumHelper.selectFromDropdown(driver, By.id("jobAdvertisementDetailsView.countries"), country);
    }

    public void selectFederalState(WebDriver driver, String federalState) {
        SeleniumHelper.selectFromDropdown(driver, By.id("jobAdvertisementDetailsView.federalStates"), federalState);
    }

    public void enterLocation(WebDriver driver, String location) {
        driver.findElement(By.id("jobAdvertisementDetailsView.location")).sendKeys(location);
    }

    public void enterStartDate(WebDriver driver, String startDate) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDetailsView.startDate.input"));
        element.clear();
        element.sendKeys(startDate);
    }

    public void enterEndDate(WebDriver driver, String endDate) {
        WebElement element = driver.findElement(By.id("jobAdvertisementDetailsView.endDate.input"));
        element.clear();
        element.sendKeys(endDate);
    }

    public void enterDuration(WebDriver driver, String duration) {
        driver.findElement(By.id("jobAdvertisementDetailsView.duration")).sendKeys(duration);
    }

    public String getNote(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementView.message.note")).getAttribute("value");
    }

    public void clickSave(WebDriver driver) {
        driver.findElement(By.id("jobAdvertisementDetailsView.save")).click();
    }
}

