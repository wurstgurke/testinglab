package de.neusta.b4u.binding.contact;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by zih on 4/20/17.
 */
public class ContactCreatePage extends BasePage {
    public ContactCreatePage() {
        super("#/contact/create");
    }

    protected ContactCreatePage(String path) {
        super(path);
    }

    public boolean isDisplayed(WebDriver driver) {
        return driver.getCurrentUrl().contains("#/contact/create");
    }

    public void enterName(WebDriver driver, String name) {
        WebElement element = driver.findElement(By.id("contactView.name"));
        element.clear();
        element.sendKeys(name);
    }

    public void enterForPerson(WebDriver driver, String forPerson) {
        WebElement element = driver.findElement(By.id("contactView.forPerson"));
        element = element.findElement(By.tagName("input"));
        element.clear();
        element.sendKeys(forPerson);

        SeleniumHelper.waitForAngularFinished(driver);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void enterWithPerson(WebDriver driver, String withPerson) {
        WebElement element = driver.findElement(By.id("contactView.withPerson"));
        element = element.findElement(By.tagName("input"));
        element.clear();
        element.sendKeys(withPerson);

        SeleniumHelper.waitForAngularFinished(driver);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void enterWithCompany(WebDriver driver, String withCompany) {
        WebElement element = driver.findElement(By.id("contactView.withCompany"));
        element = element.findElement(By.tagName("input"));
        element.clear();
        element.sendKeys(withCompany);

        try {
            Thread.sleep(800);
        } catch (Exception e) { }

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void clickSave(WebDriver driver) {
        driver.findElement(By.id("contactView.save")).click();
    }

    public void clickNewNote(WebDriver driver) {
        WebElement element = driver.findElement(By.cssSelector(".contact-search .float-right"));
        element.click();
    }

    public void enterNewNote(WebDriver driver, String note) {
        WebElement element = driver.findElement(By.cssSelector("#universal-modal textarea"));
        element.sendKeys(note);
    }

    public void clickSaveNote(WebDriver driver) {
        driver.findElement(By.id("universalModal.save")).click();
    }
}
