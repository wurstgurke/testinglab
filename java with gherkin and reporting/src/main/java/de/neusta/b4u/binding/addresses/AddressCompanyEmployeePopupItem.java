package de.neusta.b4u.binding.addresses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 9/12/17.
 */
public class AddressCompanyEmployeePopupItem {
    protected final int index;
    protected final WebElement employeePopupItem;

    public AddressCompanyEmployeePopupItem(int index, WebElement employeePopupItem) {
        this.index = index;
        this.employeePopupItem = employeePopupItem;
    }

    public String getName() {
        return getColumnElement(0).getText();
    }

    public String getEmail() {
        return getColumnElement(1).getText();
    }

    public String getCompany() {
        return getColumnElement(2).getText();
    }

    public void clickAddToCompany() {
        this.employeePopupItem.click();
    }

    private WebElement getColumnElement(int index) {
        return this.employeePopupItem.findElements(By.tagName("td")).get(index);
    }
}
