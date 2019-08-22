package de.neusta.b4u.binding.addresses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 4/20/17.
 */
public class AddressListPersonItem {
    protected final int index;
    protected final WebElement addressTableRow;

    public AddressListPersonItem(int index, WebElement addressTableRow) {
        this.index = index;
        this.addressTableRow = addressTableRow;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return getColumnElement("name").getText();
    }

    public String getCompanyName() {
        return getColumnElement("companyName").getText();
    }

    public String getStreet() {
        return getColumnElement("address.street").getText();
    }

    public String getZipCode() {
        return getColumnElement("address.zipCode").getText();
    }

    public String getCity() {
        return getColumnElement("address.city").getText();
    }

    public String getPhone() {
        return getColumnElement("phone.number").getText();
    }

    public void clickEdit() {
        WebElement editButton = getColumnElement("name");
        editButton.click();
    }

    public void clickRemove() {
        WebElement removeButton = this.addressTableRow.findElement(
                By.id("personAddressTableView.client.client[" + this.index + "].remove"));
        removeButton.click();
    }

    private WebElement getColumnElement(String id) {
        return this.addressTableRow.findElement(By.id("personAddressTableView.client[" + this.index + "]." + id));
    }
}
