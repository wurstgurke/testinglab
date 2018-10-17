package de.neusta.b4u.binding.addresses;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by zih on 4/20/17.
 */
public class AddressCreateCompanyPage extends BasePage {
    public AddressCreateCompanyPage() {
        super("#/company/create");
    }

    protected AddressCreateCompanyPage(String path) {
        super(path);
    }

    public enum ContactType {
        Customer("contactTypeFormView.contactTypes[0].CUSTOMER"),
        Freelancer("contactTypeFormView.contactTypes[1].FREELANCER"),
        Partner("contactTypeFormView.contactTypes[2].PARTNER"),
        Supplier("contactTypeFormView.contactTypes[3].SUPPLIER"),
        Interested("contactTypeFormView.contactTypes[4].INTERESTED"),
        TeamNeusta("contactTypeFormView.contactTypes[5].TEAM_NEUSTA");

        private String identifier;
        ContactType(String identifier) { this.identifier = identifier; }

        @Override
        public String toString() { return this.identifier; }
        public static ContactType getValue(String identifier) {
            switch (identifier) {
                case "Kunde": return ContactType.Customer;
                case "Freiberufler/in": return ContactType.Freelancer;
                case "Partner": return ContactType.Partner;
                case "Lieferant": return ContactType.Supplier;
                case "team neusta": return ContactType.TeamNeusta;
                default: return ContactType.Interested;
            }
        }
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().endsWith("#/company/create");
    }

    public void enterName(WebDriver driver, String name) {
        WebElement element = driver.findElement(By.id("companyDetailsView.name"));
        element.clear();
        element.sendKeys(name);
    }

    public void enterCorporation(WebDriver driver, String corporation) {
        WebElement element = driver.findElement(By.id("companyDetailsView.corporation"));
        element.clear();
        element.sendKeys(corporation);
    }

    public void enterUrl(WebDriver driver, String url) {
        WebElement element = driver.findElement(By.id("companyDetailsView.url"));
        element.clear();
        element.sendKeys(url);
    }

    public void enterEmail(WebDriver driver, String email) {
        WebElement element = driver.findElement(By.id("companyDetailsView.email"));
        element.clear();
        element.sendKeys(email);
    }

    public void enterNewLocationName(WebDriver driver, String name) {
//        WebElement element = SeleniumHelper.findLastElement(driver, By.id("locationsView.location.0.name"));
        WebElement element = driver.findElement(By.id("locationsView.location.0.name"));
        element.clear();
        element.sendKeys(name);
    }

    public void enterNewLocationStreet(WebDriver driver, String street) {
//        WebElement element = SeleniumHelper.findLastElement(driver, By.id("locationsView.location.0.street"));
        WebElement element = driver.findElement(By.id("locationsView.location.0.street"));
        element.clear();
        element.sendKeys(street);
    }

    public void enterNewLocationZipCode(WebDriver driver, String zipCode) {
//        WebElement element = SeleniumHelper.findLastElement(driver, By.id("locationsView.location.0.zipCode"));
        WebElement element = driver.findElement(By.id("locationsView.location.0.zipCode"));
        element.clear();
        element.sendKeys(zipCode);
    }

    public void enterNewLocationCity(WebDriver driver, String city) {
//        WebElement element = SeleniumHelper.findLastElement(driver, By.id("locationsView.location.0.city"));
        WebElement element = driver.findElement(By.id("locationsView.location.0.city"));
        element.clear();
        element.sendKeys(city);
    }

    public void selectNewLocationCountry(WebDriver driver, String city) {
//        SeleniumHelper.selectFromDropdown(driver, By.id("locationView.location.0.countryList"), city, true);
        SeleniumHelper.selectFromDropdown(driver, By.id("companyLocationslocation.0.countries"), city);
    }

    public void enterNewLocationPhone(WebDriver driver, String phone) {
        String[] split = phone.split(": ");

//        SeleniumHelper.selectFromDropdown(driver, By.id("newPhoneFormView.phoneTypes"), split[0], true);
        //SeleniumHelper.selectFromDropdown(driver, By.id("phoneFormView.phone[0].phoneTypes"), split[0]);
        //driver.findElement(By.id("phoneFormView.phone[0].numberEdit")).sendKeys(split[1]);
        //driver.findElement(By.id("phoneFormView.add")).click();

        SeleniumHelper.selectFromDropdown(driver, By.id("valueFormView.value[0].types"), split[0]);
        driver.findElement(By.id("valueFormView.value[0].edit")).sendKeys(split[1]);
        driver.findElement(By.id("valueFormView.add")).click();
    }

    public void clickAddNewLocation(WebDriver driver) {
        driver.findElement(By.id("newLocationView.add")).click();
    }

    public void selectContactType(WebDriver driver, ContactType contactType) {
        driver.findElement(By.id(contactType.toString())).click();
    }

    public void clickSave(WebDriver driver) {
        // FIXME: dirty hack to get the save button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("companyDetailsView.save")).click();
    }
}
