package de.neusta.b4u.binding.addresses;

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
public class AddressListPersonPage extends BasePage {
    public AddressListPersonPage() {
        super("#/addresslist/person");
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().endsWith("#/addresslist/person");
    }

    public void enterNameOrId(WebDriver driver, String nameOrId) {
        WebElement element = driver.findElement(By.id("personAddressTableView.nameOrId.md"));
        element = element.findElement(By.tagName("input"));
        element.clear();
        element.sendKeys(nameOrId);

        SeleniumHelper.waitForAngularFinished(driver);

        enterEmail(driver, "");

        SeleniumHelper.waitForAngularFinished(driver);
    }

    public void enterPhone(WebDriver driver, String phone) {
        driver.findElement(By.id("personAddressTableView.phone")).sendKeys(phone);
    }

    public void enterEmail(WebDriver driver, String email) {
        driver.findElement(By.id("personAddressTableView.email")).sendKeys(email);
    }

    public void enterCompany(WebDriver driver, String company) {
        driver.findElement(By.id("personAddressTableView.company")).sendKeys(company);
    }

    public void enterZipCode(WebDriver driver, String zipCode) {
        driver.findElement(By.id("personAddressTableView.zipCode")).sendKeys(zipCode);
    }

    public void enterCity(WebDriver driver, String city) {
        driver.findElement(By.id("personAddressTableView.city")).sendKeys(city);
    }

    public void enterStreet(WebDriver driver, String street) {
        driver.findElement(By.id("personAddressTableView.street")).sendKeys(street);
    }

    public void selectContactType(WebDriver driver, String contactType) {
        SeleniumHelper.selectFromDropdown(driver, By.id("personAddressTableView.contactTypes"), contactType);
    }

    public void enterKeywords(WebDriver driver, String keywords) {
        driver.findElement(By.id("personAddressTableView.searchKeywords")).sendKeys(keywords);
    }

    public void clickSearch(WebDriver driver) {
        //driver.findElement(By.id("personAddressTableView.search1")).click();
        driver.findElement(By.id("personAddressTableView.search2")).click();
    }

    public void clickCreatePerson(WebDriver driver) {
        driver.findElement(By.id("personAddressTableView.create")).click();
    }

    public void clickClearFilter(WebDriver driver) {
        driver.findElement(By.id("personAddressTableView.search.clearFilter")).click();
    }

    public List<AddressListPersonItem> getSearchResultItems(WebDriver driver, boolean freelancer) {
        WebElement personAddressTableView = driver.findElement(By.id("personAddressTableView.table"));

        List<AddressListPersonItem> searchResults = new ArrayList<>();

        // get all address table entries
        List<WebElement> addressTableRows = personAddressTableView.findElements(By.cssSelector("tbody>tr"));

        for (int i = 0; i < addressTableRows.size();++i) {
            AddressListPersonItem addressListPersonItem = null;
            if (!freelancer)
                addressListPersonItem = new AddressListPersonItem(i, addressTableRows.get(i));
            else
                addressListPersonItem = new AddressListFreelancerItem(i, addressTableRows.get(i));

            searchResults.add(addressListPersonItem);
        }

        return searchResults;
    }

    public boolean isSearchFormEmpty(WebDriver driver) {
        return driver.findElement(By.id("personAddressTableView.nameOrId")).getText().isEmpty() &&
                driver.findElement(By.id("personAddressTableView.phone")).getText().isEmpty() &&
                driver.findElement(By.id("personAddressTableView.email")).getText().isEmpty() &&
                driver.findElement(By.id("personAddressTableView.company")).getText().isEmpty() &&
                driver.findElement(By.id("personAddressTableView.zipCode")).getText().isEmpty() &&
                driver.findElement(By.id("personAddressTableView.city")).getText().isEmpty() &&
                driver.findElement(By.id("personAddressTableView.street")).getText().isEmpty();
    }
}
