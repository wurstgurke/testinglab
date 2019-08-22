package de.neusta.b4u.binding.addresses;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 4/20/17.
 */
public class AddressCreatePersonPage extends BasePage {
    private final String prefix;

    public AddressCreatePersonPage() {
        super("#/person/create");
        prefix = "personDetailsFormView";
    }

    protected AddressCreatePersonPage(String path) {
        super(path);
        prefix = "personDetailsFormView";
    }

    public enum ContactType {
        Customer("contactTypeFormView.contactTypes[0].CUSTOMER"),
        Freelancer("contactTypeFormView.contactTypes[1].FREELANCER"),
        Partner("contactTypeFormView.contactTypes[2].PARTNER"),
        Supplier("contactTypeFormView.contactTypes[3].SUPPLIER"),
        Employee("contactTypeFormView.contactTypes[4].EMPLOYEE"),
        EmployeeExtern("contactTypeFormView.contactTypes[5].EMPLOYEE_EXTERN"),
        Interested("contactTypeFormView.contactTypes[6].INTERESTED"),
        TeamNeusta("contactTypeFormView.contactTypes[7].TEAM_NEUSTA");

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
                case "Mitarbeiter/in": return ContactType.Employee;
                case "Angestellte/r (extern)": return ContactType.EmployeeExtern;
                case "team neusta": return ContactType.TeamNeusta;
                default: return ContactType.Interested;
            }
        }
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().endsWith("#/person/create");
    }

    public void selectSalutation(WebDriver driver, String salutation) {
        SeleniumHelper.selectFromDropdown(driver, By.id(prefix+".salutationTypes"), salutation);
    }

    public void enterFirstName(WebDriver driver, String firstname) {
        WebElement element = driver.findElement(By.id(prefix+".firstName"));
        element.clear();
        element.sendKeys(firstname);
    }

    public void enterLastName(WebDriver driver, String lastname) {
        WebElement element = driver.findElement(By.id(prefix+".lastName"));
        element.clear();
        element.sendKeys(lastname);
    }

    public void enterPosition(WebDriver driver, String position) {
        WebElement element = driver.findElement(By.id(prefix+".position"));
        element.clear();
        element.sendKeys(position);
    }

    public void enterDepartment(WebDriver driver, String department) {
        WebElement element = driver.findElement(By.id(prefix+".department"));
        element.clear();
        element.sendKeys(department);
    }

    public void enterPhone(WebDriver driver, String phone) {
        String[] split = phone.split(": ");

//        SeleniumHelper.selectFromDropdown(driver, By.id("phoneFormView.phone[0].phoneTypes"), split[0]);
//        driver.findElement(By.id("phoneFormView.phone[0].numberEdit")).sendKeys(split[1]);
//        driver.findElement(By.id("phoneFormView.add")).click();

        WebElement element = driver.findElement(By.id("phoneFormView"));
        WebElement dropdown = element.findElement(By.id("valueFormView.value[0].types"));

        SeleniumHelper.selectFromDropdown(driver, dropdown, split[0]);
        element.findElement(By.id("valueFormView.value[0].edit")).sendKeys(split[1]);
        element.findElement(By.id("valueFormView.add")).click();
    }

    public void enterWebsite(WebDriver driver, String website) {
        WebElement element = driver.findElement(By.id("linkFormView"));

        element.findElement(By.id("valueFormView.value[0].edit")).sendKeys(website);
        element.findElement(By.id("valueFormView.add")).click();
    }

    public void enterEmail(WebDriver driver, String email) {
        String[] split = email.split(": ");

        SeleniumHelper.selectFromDropdown(driver, By.id("emailAdressFormView.emailAddress[0].emailAddressTypes"), split[0]);
        driver.findElement(By.id("emailAdressFormView.emailAddress[0].address")).sendKeys(split[1]);
        driver.findElement(By.id("newEmailAddressFormView.add")).click();
    }

    public void enterBirthdate(WebDriver driver, String birthdate) {
        WebElement element = driver.findElement(By.id(prefix+".birthdate.input"));
        element.clear();
        element.sendKeys(birthdate);
    }

    public void enterStreet(WebDriver driver, String street) {
        WebElement element = driver.findElement(By.id("addressFormView.address[0].street"));
        element.clear();
        element.sendKeys(street);
    }

    public void enterZipCode(WebDriver driver, String zipCode) {
        WebElement element = driver.findElement(By.id("addressFormView.address[0].zipCode"));
        element.clear();
        element.sendKeys(zipCode);
    }

    public void enterCity(WebDriver driver, String city) {
        WebElement element = driver.findElement(By.id("addressFormView.address[0].city"));
        element.clear();
        element.sendKeys(city);
    }

    public void selectCountry(WebDriver driver, String country) {
        SeleniumHelper.selectFromDropdown(driver, By.id("addressFormView.address[0].countries"), country);
    }

    public void selectAddressType(WebDriver driver, String addressType) {
        SeleniumHelper.selectFromDropdown(driver, By.id("addressFormView.address[0].addressTypes"), addressType);
    }

    public void selectContactType(WebDriver driver, ContactType contactType) {
        driver.findElement(By.id(contactType.toString())).click();
    }

    public void clickSave(WebDriver driver) {
        // FIXME: dirty hack to get the edit button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.cssSelector("header>button")).click();
        //driver.findElement(By.id("personCreateFormView.create")).click();
        //driver.findElement(By.id("personCreateFormView.save")).click();
    }
}
