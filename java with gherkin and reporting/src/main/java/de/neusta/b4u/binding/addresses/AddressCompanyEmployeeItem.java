package de.neusta.b4u.binding.addresses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 9/12/17.
 */
public class AddressCompanyEmployeeItem {
    protected final int index;
    protected final WebElement employeeItem;

    public AddressCompanyEmployeeItem(int index, WebElement employeeItem) {
        this.index = index;
        this.employeeItem = employeeItem;
    }

    public String getName() {
        return getColumnElement("name").getText();
    }

    public String getPosition() {
        return getColumnElement("position").getText();

    }

    public String getDepartment() {
        return getColumnElement("department").getText();
    }

    private WebElement getColumnElement(String id) {
        return this.employeeItem.findElement(By.id("personListView.person[" + this.index + "]." + id));
    }
}
