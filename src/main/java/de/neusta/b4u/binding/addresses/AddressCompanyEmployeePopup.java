package de.neusta.b4u.binding.addresses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zih on 9/12/17.
 */
public class AddressCompanyEmployeePopup {
    public boolean isDisplayed(WebDriver driver) {
        return !driver.findElements(By.id("addEmployeeToLocationView.table")).isEmpty();
    }

    public void enterName(WebDriver driver, String firstName) {
        driver.findElement(By.id("addEmployeeToLocationView.name")).sendKeys(firstName);
    }

    public void enterEmail(WebDriver driver, String email) {
        driver.findElement(By.id("addEmployeeToLocationView.emailAddress")).sendKeys(email);
    }

    public void clickSearch(WebDriver driver) {
        driver.findElement(By.id("addEmployeeToLocationView.search1")).click();
    }

    public void clickClearFilter(WebDriver driver) {
        driver.findElement(By.id("addEmployeeToLocationView.clear")).click();
    }

    public List<AddressCompanyEmployeePopupItem> getEmployeeList(WebDriver driver) {
        WebElement employeeListTableView = driver.findElement(By.id("addEmployeeToLocationView.table"));

        List<AddressCompanyEmployeePopupItem> employeeList = new ArrayList<>();

        List<WebElement> employeeListTableRows = employeeListTableView.findElements(By.cssSelector("tbody>tr"));
        for (int i = 0; i < employeeListTableRows.size();++i) {
            AddressCompanyEmployeePopupItem addressEmployeePopupItem =
                    new AddressCompanyEmployeePopupItem(i, employeeListTableRows.get(i));

            employeeList.add(addressEmployeePopupItem);
        }

        return employeeList;
    }
}
