package de.neusta.b4u.binding.addresses;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by zih on 6/21/17.
 */
public class AddressEditPersonProjectPage extends BasePage {
    public AddressEditPersonProjectPage() {
        super("");
    }

    public boolean isDisplayed(WebDriver driver) {
        return SeleniumHelper.elementExists(driver, By.id("editTaskView.title"));
    }

    public void enterOrderNumber(WebDriver driver, String orderNumber) {
        driver.findElement(By.id("projectDetails.orderNr")).sendKeys(orderNumber);
    }

    public void enterStartDate(WebDriver driver, String startDate) {
        driver.findElement(By.id("projectDetails.startDate.input")).sendKeys(startDate);
    }

    public void enterEndDate(WebDriver driver, String endDate) {
        driver.findElement(By.id("projectDetails.endDate.input")).sendKeys(endDate);
    }

    public void enterPosition(WebDriver driver, String position) {
        driver.findElement(By.id("projectDetails.position")).sendKeys(position);
    }

    public void enterCustomerTrade(WebDriver driver, String position) {
        driver.findElement(By.id("projectDetails.customerTrade")).sendKeys(position);
    }

    public void enterDescription(WebDriver driver, String description) {
        driver.findElement(By.id("projectDetails.description")).sendKeys(description);
    }

    public void enterTechnicalDescription(WebDriver driver, String technicalDescription) {
        driver.findElement(By.id("projectDetails.technicalDescription")).sendKeys(technicalDescription);
    }

    public void clickSave(WebDriver driver) {
        driver.findElement(By.id("projectDetails.save")).click();
    }
}
