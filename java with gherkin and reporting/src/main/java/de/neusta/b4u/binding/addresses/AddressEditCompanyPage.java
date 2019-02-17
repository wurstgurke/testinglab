package de.neusta.b4u.binding.addresses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zih on 5/8/17.
 */
public class AddressEditCompanyPage extends AddressCreateCompanyPage {
    public AddressEditCompanyPage(String companyId) {
        super("#/company/edit/" + companyId);
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().contains("#/company/edit/");
    }

    public String getName(WebDriver driver) {
        return driver.findElement(By.id("companyDetailsView.name")).getAttribute("value");
    }

    public String getCorporation(WebDriver driver) {
        return driver.findElement(By.id("companyDetailsView.corporation")).getAttribute("value");
    }

    public String getUrl(WebDriver driver) {
        return driver.findElement(By.id("companyDetailsView.url")).getAttribute("value");
    }

    public String getEmail(WebDriver driver) {
        return driver.findElement(By.id("companyDetailsView.email")).getAttribute("value");
    }

    public String getLocationAddress(WebDriver driver) {
        WebElement element = driver.findElement(By.tagName("address"));
        return element.findElement(By.tagName("span")).getText();
    }

    public String getLocationPhone(WebDriver driver) {
        WebElement element = driver.findElement(By.tagName("address"));
        return element.findElement(By.tagName("li")).getText();
    }

    public List<ContactType> getContactTypes(WebDriver driver) {
        List<WebElement> contactTypeElements =
                driver.findElements(By.cssSelector(".contactTypeForm span"));

        List<ContactType> contactTypes = new ArrayList<>();
        for (WebElement element : contactTypeElements) {
            contactTypes.add(ContactType.getValue(element.getText()));
        }

        return contactTypes;
    }

    public void clickEditDetails(WebDriver driver) {
        // FIXME: dirty hack to get the edit button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("companyDetailsView.edit")).click();
    }

    public void clickSaveDetails(WebDriver driver) {
        driver.findElement(By.id("companyDetailsView.saveDetails")).click();
    }

    public void clickEditLocation(WebDriver driver) {
        driver.findElement(By.id("companyDetailsView.editAddress")).click();
    }

    public void clickSaveLocation(WebDriver driver) {
        driver.findElement(By.id("companyDetailsView.saveAdress")).click();
    }

    public void clickEditContactTypes(WebDriver driver) {
        driver.findElement(By.id("clientContactTypeFormView.edit")).click();
    }

    public void clickSaveContactTypes(WebDriver driver) {
        driver.findElement(By.id("clientContactTypeFormView.save")).click();
    }

    public void clickAddNewPerson(WebDriver driver) {
        driver.findElement(By.id("companyDetailsView.createPerson")).click();
    }

    public void openContactTab(WebDriver driver) {
        driver.findElement(By.id("companyTabsView.contacts")).click();
    }

    public void clickAddContact(WebDriver driver) {
        driver.findElement(By.id("contactListTableView.create1")).click();
    }

    public List<AddressEditContactItem> getContactList(WebDriver driver) {
        WebElement contactListTableView = driver.findElement(By.id("contactListTableView.table"));

        List<AddressEditContactItem> contactList = new ArrayList<>();

        List<WebElement> contactListTableRows = contactListTableView.findElements(By.cssSelector("tbody>tr"));
        for (int i = 0; i < contactListTableRows.size();++i) {
            AddressEditContactItem addressEditContactItem =
                    new AddressEditContactItem(i, contactListTableRows.get(i));

            contactList.add(addressEditContactItem);
        }

        return contactList;
    }

    public void openSkillTab(WebDriver driver) {
        driver.findElement(By.id("companyTabsView.skills")).click();
    }

    public void addSkill(WebDriver driver, String skill) {
        driver.findElement(By.id("companySkillsView.create")).sendKeys(skill);
        driver.findElement(By.id("companySkillsView.add")).click();
    }

    public void removeSkill(WebDriver driver, String skill) {
        WebElement element = driver.findElement(By.tagName("company-skills"));

        // find the specific element
        for (WebElement entry : element.findElements(By.cssSelector("li>span"))) {
            if (entry.getText().equals(skill)) {
                // perform hover action
                Actions action = new Actions(driver);
                action.moveToElement(entry).build().perform();

                try {
                    Thread.sleep(100);
                } catch (Exception e) {}

                entry.findElement(By.id("companySkillsView.delete")).click();

                break;
            }
        }
    }

    public List<String> getSkills(WebDriver driver) {
        List<String> skillList = new ArrayList<>();

        WebElement element = driver.findElement(By.tagName("company-skills"));
        for (WebElement entry : element.findElements(By.cssSelector("li>span"))) {
            skillList.add(entry.getText());
        }

        return skillList;
    }

    public void openEmployeeList(WebDriver driver) {
        driver.findElement(By.id("locationsView.location.0.openEmployerList")).click();
    }

    public void openAddEmployeeList(WebDriver driver) {
        driver.findElement(By.id("locationsView.location.0.openAddEmployee")).click();
    }

    public List<AddressCompanyEmployeeItem> getEmployeeList(WebDriver driver) {
        WebElement employeeListTableView = driver.findElement(By.id("personListView.table"));

        List<AddressCompanyEmployeeItem> employeeList = new ArrayList<>();

        List<WebElement> employeeListTableRows = employeeListTableView.findElements(By.cssSelector("tbody>tr"));
        for (int i = 0; i < employeeListTableRows.size();++i) {
            AddressCompanyEmployeeItem addressEmployeeItem =
                    new AddressCompanyEmployeeItem(i, employeeListTableRows.get(i));

            employeeList.add(addressEmployeeItem);
        }

        return employeeList;
    }
}
