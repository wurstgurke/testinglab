package de.neusta.b4u.binding.jobadvertisement;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zih on 4/6/17.
 */
public class JobAdvertisementListPage extends BasePage {
    public JobAdvertisementListPage() {
        super("#/job/list");
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().endsWith("#/job/list");
    }

    public void enterId(WebDriver driver, String id) {
        driver.findElement(By.id("jobAdvertisementListView.id")).sendKeys(id);
    }

    public void enterPosition(WebDriver driver, String position) {
        driver.findElement(By.id("jobAdvertisementListView.position")).sendKeys(position);
    }

    public void enterCustomer(WebDriver driver, String customer) {
        SeleniumHelper.selectFromDropdown(driver, By.id("jobAdvertisementListView.selectedCustomer"), customer);
    }

    public void enterStartDate(WebDriver driver, String startDate) {
        driver.findElement(By.id("jobAdvertisementListView.startDate.input")).sendKeys(startDate);
    }

    public void enterEndDate(WebDriver driver, String endDate) {
        driver.findElement(By.id("jobAdvertisementListView.endDate.input")).sendKeys(endDate);
    }

    public void selectForPerson(WebDriver driver, String forPerson) {
        SeleniumHelper.selectFromDropdown(driver, By.id("jobAdvertisementListView.selectedForPerson"), forPerson);
    }

    public void selectKeyAccountManager(WebDriver driver, String keyAccountManager) {
        SeleniumHelper.selectFromDropdown(driver, By.id("jobAdvertisementListView.selectedKeyAccountManager"), keyAccountManager);
    }

    public void selectState(WebDriver driver, String state) {
        SeleniumHelper.selectFromDropdown(driver, By.id("jobAdvertisement.stateTypes"), state);
    }

    public void clickSearch(WebDriver driver) {
        driver.findElement(By.id("jobAdvertisementListView.search1")).click();
        //driver.findElement(By.id("jobAdvertisementListView.search2")).click();
    }

    public void clickPublish(WebDriver driver) {
        driver.findElement(By.id("jobAdvertisementListView.publish")).click();
    }

    public void clickClearFilter(WebDriver driver) {
        driver.findElement(By.id("jobAdvertisementListView.clear")).click();
    }

    public List<JobAdvertisementListItem> getSearchResultItems(WebDriver driver) {
        WebElement advertisementListView = driver.findElement(By.id("jobAdvertisementListView.table"));

        List<JobAdvertisementListItem> searchResults = new ArrayList<>();

        // get all address table entries
        List<WebElement> advertisementTableRows = advertisementListView.findElements(By.cssSelector("tbody>tr"));

        for (int i = 0; i < advertisementTableRows.size();++i) {
            JobAdvertisementListItem jobAdvertisementListItem = new JobAdvertisementListItem(i, advertisementTableRows.get(i));
            searchResults.add(jobAdvertisementListItem);
        }

        return searchResults;
    }

    public boolean isSearchFormEmpty(WebDriver driver) {
        return driver.findElement(By.id("jobAdvertisementListView.id")).getText().isEmpty() &&
                driver.findElement(By.id("jobAdvertisementListView.position")).getText().isEmpty() &&
                driver.findElement(By.id("jobAdvertisementListView.startDate.input")).getText().isEmpty() &&
                driver.findElement(By.id("jobAdvertisementListView.endDate.input")).getText().isEmpty();
    }
}
