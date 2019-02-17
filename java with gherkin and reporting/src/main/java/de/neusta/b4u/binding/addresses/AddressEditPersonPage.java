package de.neusta.b4u.binding.addresses;

import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zih on 5/8/17.
 */
public class AddressEditPersonPage extends AddressCreatePersonPage {
    public AddressEditPersonPage(String personId) {
        super("#/person/edit/" + personId);
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().contains("#/person/edit/");
    }

    public String getName(WebDriver driver) {
        List<WebElement> elements = getPersonDetailsRow(driver, 1).findElements(By.tagName("span"));
        return elements.get(elements.size()-1).getText();
    }

    public String getPosition(WebDriver driver) {
        return driver.findElement(By.id("personDetailsFormView.position")).getAttribute("value");
    }

    public String getDepartment(WebDriver driver) {
        return driver.findElement(By.id("personDetailsFormView.department")).getAttribute("value");
    }

    public String getPhone(WebDriver driver) {
        WebElement phoneFormElement = driver.findElement(By.id("phoneFormView"));
        return phoneFormElement.findElement(By.tagName("div")).getText().replace("\n", " ");
    }

    public String getWebsite(WebDriver driver) {
        WebElement linkFormElement = driver.findElement(By.cssSelector("#linkFormView+div a"));
        return linkFormElement.getAttribute("href");
    }

    public String getEmail(WebDriver driver) {
        WebElement emailAddressFormElement = driver.findElement(By.tagName("email-address-form"));
        return emailAddressFormElement.findElement(By.tagName("div")).getText().replace("\n", " ");
    }

    public String getBirthdate(WebDriver driver) {
        return driver.findElement(By.id("personDetailsFormView.birthdate")).getText();
    }

    public String getAddress(WebDriver driver) {
        WebElement element = driver.findElements(By.tagName("address")).get(1);
        return element.findElement(By.tagName("div")).getText();
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

        driver.findElement(By.id("personDetailsFormView.edit")).click();
    }

    public void clickSaveDetails(WebDriver driver) {
        driver.findElement(By.id("personDetailsFormView.update")).click();
    }

    public void clickEditAddress(WebDriver driver) {
        driver.findElement(By.id("personAddressesFormView.edit")).click();
    }

    public void clickSaveAddress(WebDriver driver) {
        driver.findElement(By.id("personAddressesFormView.save")).click();
    }

    public void clickEditContactTypes(WebDriver driver) {
        driver.findElement(By.id("clientContactTypeFormView.edit")).click();
    }

    public void clickSaveContactTypes(WebDriver driver) {
        driver.findElement(By.id("clientContactTypeFormView.save")).click();
    }

    private WebElement getPersonDetailsRow(WebDriver driver, int index) {
        List<WebElement> personDetailsList =
                driver.findElements(By.cssSelector(".personal-data li"));
        return personDetailsList.get(index);
    }

    public void openContactTab(WebDriver driver) {
        // FIXME: dirty hack to get the create button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("person-tab-contact-list")).click();
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

    public void openKnowHowTab(WebDriver driver) {
        // FIXME: dirty hack to get the create button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("person-tab-know-how")).click();
    }

    public void addKnowHow(WebDriver driver, String knowHow) {
        WebElement element = driver.findElement(By.id("addKnowHowsView.selectedKnowHow"));
        element.sendKeys(knowHow);

        try {
            Thread.sleep(500);
        } catch(Exception e) { }

        element.sendKeys(Keys.RETURN);
    }

    public void removeKnowHow(WebDriver driver, String knowHow) {
        WebElement element = driver.findElement(By.tagName("person-know-how"));

        // find the specific element
        for (WebElement entry : element.findElements(By.cssSelector("li>span"))) {
            if (entry.getText().equals(knowHow)) {
                String entryId = entry.findElement(By.xpath("..")).getAttribute("id");

                // perform hover action
                Actions action = new Actions(driver);
                action.moveToElement(entry).build().perform();

                try {
                    Thread.sleep(100);
                } catch (Exception e) {}

                driver.findElement(By.id(entryId + ".close")).click();

                break;
            }
        }
    }

    public List<String> getKnowHows(WebDriver driver) {
        List<String> knowHowList = new ArrayList<>();

        WebElement element = driver.findElement(By.tagName("person-know-how"));
        for (WebElement entry : element.findElements(By.cssSelector("li>span"))) {
            knowHowList.add(entry.getText());
        }

        return knowHowList;
    }

    public void openSkillTab(WebDriver driver) {
        // FIXME: dirty hack to get the create button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("person-tab-skills")).click();
    }

    public void addSkill(WebDriver driver, String skill) {
        driver.findElement(By.id("personSkillsView.create")).sendKeys(skill);
        driver.findElement(By.id("personSkillsView.skillSubmit")).click();
    }

    public void removeSkill(WebDriver driver, String skill) {
        WebElement element = driver.findElement(By.tagName("person-skills"));

        // find the specific element
        for (WebElement entry : element.findElements(By.cssSelector("li>span"))) {
            if (entry.getText().equals(skill)) {
                String entryId = entry.findElement(By.xpath("..")).getAttribute("id");

                // perform hover action
                Actions action = new Actions(driver);
                action.moveToElement(entry).build().perform();

                try {
                    Thread.sleep(100);
                } catch (Exception e) {}

                driver.findElement(By.id(entryId + ".delete")).click();

                break;
            }
        }
    }

    public List<String> getSkills(WebDriver driver) {
        List<String> skillList = new ArrayList<>();

        WebElement element = driver.findElement(By.tagName("person-skills"));
        for (WebElement entry : element.findElements(By.cssSelector("li>span"))) {
            skillList.add(entry.getText());
        }

        return skillList;
    }

    public void openProjectTab(WebDriver driver)
    {
        // FIXME: dirty hack to get the create button clickable
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

        driver.findElement(By.id("person-tab-projects")).click();
    }

    public void clickAddProject(WebDriver driver) {
        driver.findElement(By.id("personProjectsView.create1")).click();
    }

    public List<AddressEditPersonProjectItem> getProjectList(WebDriver driver) {
        WebElement projectListTableView = driver.findElement(By.id("personProjectsView.table"));

        List<AddressEditPersonProjectItem> projectList = new ArrayList<>();

        List<WebElement> projectListTableRows = projectListTableView.findElements(By.cssSelector("tbody>tr"));
        for (int i = 0; i < projectListTableRows.size();++i) {
            AddressEditPersonProjectItem addressEditPersonProjectItem =
                    new AddressEditPersonProjectItem(i, projectListTableRows.get(i));

            projectList.add(addressEditPersonProjectItem);
        }

        return projectList;
    }

    public void clickEditFreelancerDetails(WebDriver driver) {
        driver.findElement(By.id("freelancerFormView.edit")).click();
    }

    public void clickSaveFreelancerDetails(WebDriver driver) {
        driver.findElement(By.id("freelancerFormView.save")).click();
    }

    public void enterCooperationSince(WebDriver driver, String cooperationSince) {
        WebElement element = driver.findElement(By.id("freelancerFormView.cooperationSince.input"));
        element.clear();
        element.sendKeys(cooperationSince);
    }

    public String getCooperationSince(WebDriver driver) {
        return driver.findElement(By.id("freelancerFormView.cooperationSince.text")).getText();
    }

    public void enterAvailableSince(WebDriver driver, String availableSince) {
        WebElement element = driver.findElement(By.id("freelancerFormView.availableSince.input"));
        element.clear();
        element.sendKeys(availableSince);
    }

    public String getAvailableSince(WebDriver driver) {
        return driver.findElement(By.id("freelancerFormView.availableSince.text")).getText();
    }

    public void selectFreelancerState(WebDriver driver, String freelancerState) {
        SeleniumHelper.selectFromDropdown(driver, By.id("freelancerFormView.freelancerStates"), freelancerState);
    }

    public String getFreelancerState(WebDriver driver) {
        return driver.findElement(By.id("freelancerFormView.freelancerStates.text")).getText();
    }

    public void enterHourlyRateMin(WebDriver driver, String hourlyRateMin) {
        final String[] split = hourlyRateMin.split(" ");

        WebElement element = driver.findElement(By.id("freelancerFormView.hourlyRateMin"));
        element.clear();
        element.sendKeys(split[0]);

        SeleniumHelper.selectFromDropdown(driver, By.id("freelancerFormView.hourlyRateMin.currencyCodes"), split[1]);
    }

    public String getHourlyRateMin(WebDriver driver) {
        return driver.findElement(By.id("freelancerFormView.hourlyRateMin.text")).getText();
    }

    public void enterHourlyRateMax(WebDriver driver, String hourlyRateMax) {
        final String[] split = hourlyRateMax.split(" ");

        WebElement element = driver.findElement(By.id("freelancerFormView.hourlyRateMax"));
        element.clear();
        element.sendKeys(split[0]);

        SeleniumHelper.selectFromDropdown(driver, By.id("freelancerFormView.hourlyRateMax.currencyCodes"), split[1]);
    }

    public String getHourlyRateMax(WebDriver driver) {
        return driver.findElement(By.id("freelancerFormView.hourlyRateMax.text")).getText();
    }

    public void selectContractType(WebDriver driver, String contractType) {
        SeleniumHelper.selectFromDropdown(driver, By.id("freelancerFormView.contractTypes"), contractType);
    }

    public String getContractType(WebDriver driver) {
        return driver.findElement(By.id("freelancerFormView.contractTypes.text")).getText();
    }

    public void selectRating(WebDriver driver, String rating) {
        SeleniumHelper.selectFromDropdown(driver, By.id("freelancerFormView.ratings"), rating);
    }

    public String getRating(WebDriver driver) {
        return driver.findElement(By.id("freelancerFormView.ratings.text")).getText();
    }
}
