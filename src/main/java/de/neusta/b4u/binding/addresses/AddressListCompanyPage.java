package de.neusta.b4u.binding.addresses;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zih on 4/6/17.
 */
public class AddressListCompanyPage extends BasePage {
    public AddressListCompanyPage() {
        super("#/addresslist/company");
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().endsWith("#/addresslist/company");
    }

    public void enterNameOrId(WebDriver driver, String nameOrId) {
        WebElement element = driver.findElement(By.id("companyAddressTableView.nameOrId.md"));
        element = element.findElement(By.tagName("input"));
        element.clear();
        element.sendKeys(nameOrId);

        SeleniumHelper.waitForAngularFinished(driver);

        enterSkill(driver, "");

        SeleniumHelper.waitForAngularFinished(driver);
    }

    public void enterPhone(WebDriver driver, String phone) {
        driver.findElement(By.id("companyAddressTableView.phone")).sendKeys(phone);
    }

    public void enterZipCode(WebDriver driver, String zipCode) {
        driver.findElement(By.id("companyAddressTableView.zipCode")).sendKeys(zipCode);
    }

    public void enterCity(WebDriver driver, String city) {
        driver.findElement(By.id("companyAddressTableView.city")).sendKeys(city);
    }

    public void enterStreet(WebDriver driver, String street) {
        driver.findElement(By.id("companyAddressTableView.street")).sendKeys(street);
    }

    public void enterSkill(WebDriver driver, String skill) {
        driver.findElement(By.id("companyAddressTableView.skill")).sendKeys(skill);
    }

    public void selectForPerson(WebDriver driver, String forPerson) {
        SeleniumHelper.selectFromDropdown(driver, By.id("companyAddressTableView.selectedForPerson"), forPerson);
    }

    public void clickSearch(WebDriver driver) {
        driver.findElement(By.id("companyAddressTableView.search1")).click();
        //driver.findElement(By.id("companyAddressTableView.search2")).click();
    }

    public void clickCreateCompany(WebDriver driver) {
        driver.findElement(By.id("companyAddressTableView.create")).click();
    }

    public void clickClearFilter(WebDriver driver) {
        // FIXME: dirty hack to get the edit button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("companyAddressTableView.search.clearFilter")).click();
    }

    public List<AddressListCompanyItem> getSearchResultItems(WebDriver driver) {
        WebElement companyAddressTableView = driver.findElement(By.id("companyAddressTableView.table"));

        List<AddressListCompanyItem> searchResults = new ArrayList<>();

        // get all address table entries
        List<WebElement> addressTableRows = companyAddressTableView.findElements(By.cssSelector("tbody>tr"));

        for (int i = 0; i < addressTableRows.size();++i) {
            AddressListCompanyItem addressListCompanyItem = new AddressListCompanyItem(i, addressTableRows.get(i));
            searchResults.add(addressListCompanyItem);
        }

        return searchResults;
    }

    public boolean isSearchFormEmpty(WebDriver driver) {
        return driver.findElement(By.id("companyAddressTableView.nameOrId.md")).getText().isEmpty() &&
                driver.findElement(By.id("companyAddressTableView.phone")).getText().isEmpty() &&
                driver.findElement(By.id("companyAddressTableView.zipCode")).getText().isEmpty() &&
                driver.findElement(By.id("companyAddressTableView.city")).getText().isEmpty() &&
                driver.findElement(By.id("companyAddressTableView.street")).getText().isEmpty() &&
                driver.findElement(By.id("companyAddressTableView.skill")).getText().isEmpty();
    }
}
