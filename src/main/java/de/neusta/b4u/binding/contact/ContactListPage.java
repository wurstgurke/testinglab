package de.neusta.b4u.binding.contact;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.binding.addresses.AddressListCompanyItem;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zih on 4/6/17.
 */
public class ContactListPage extends BasePage {
    public ContactListPage() {
        super("#/contact/list");
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().endsWith("#/contact/list");
    }

    public void selectSelectedForPerson(WebDriver driver, String person) {
        SeleniumHelper.selectFromDropdown(driver, By.id("contactTableView.selectedForPerson.select"), person);
    }

    public void selectSelectedForTaskType(WebDriver driver, String tasktype) {
        SeleniumHelper.selectFromDropdown(driver, By.id("contactTableView.selectedTaskType.select"), tasktype);
    }

    public void enterTitle(WebDriver driver, String title) {
        driver.findElement(By.id("contactTableView.title")).sendKeys(title);
    }

    public void enterSelectedWithPerson(WebDriver driver, String person) {
        WebElement element = driver.findElement(By.id("contactTableView.selectedWithPerson"));
        element.findElement(By.tagName("input")).sendKeys(person);
        driver.findElement(By.tagName("body")).click();
    }

    public void enterSelectedWithCompany(WebDriver driver, String company) {
        WebElement element = driver.findElement(By.id("contactTableView.selectedWithCompany"));
        element.findElement(By.tagName("input")).sendKeys(company);
        driver.findElement(By.tagName("body")).click();
    }

    public void clickSearch(WebDriver driver) {
        driver.findElement(By.id("contactTableView.search1")).click();
        //driver.findElement(By.id("contactTableView.search2")).click();
    }

    public void clickCreateContact(WebDriver driver) {
        driver.findElement(By.id("contactTableView.create")).click();
    }

    public void clickClearFilter(WebDriver driver) {
        driver.findElement(By.id("contactTableView.clear")).click();
    }

    public List<ContactListItem> getSearchResultItems(WebDriver driver) {
        WebElement contactTableView = driver.findElement(By.id("contactTableView.table"));

        List<ContactListItem> searchResults = new ArrayList<>();

        // get all address table entries
        List<WebElement> contactTableRows = contactTableView.findElements(By.cssSelector("tbody>tr"));

        for (int i = 0; i < contactTableRows.size();++i) {
            ContactListItem contactListItem = new ContactListItem(i, contactTableRows.get(i));
            searchResults.add(contactListItem);
        }

        return searchResults;
    }

    public boolean isSearchFormEmpty(WebDriver driver) {
        return driver.findElement(By.id("contactTableView.title")).getText().isEmpty() &&
                driver.findElement(By.id("contactTableView.selectedWithPerson")).getText().isEmpty() &&
                driver.findElement(By.id("contactTableView.selectedWithCompany")).getText().isEmpty();
    }
}
