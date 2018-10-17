package de.neusta.b4u.binding.addresses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 4/20/17.
 */
public class AddressListFreelancerItem extends AddressListPersonItem {
    public AddressListFreelancerItem(int index, WebElement addressTableRow) {
        super(index, addressTableRow);
    }

    public String getID() {
        return getColumnElement("id").getText();
    }

    public String getName() {
        return getColumnElement("name").getText();
    }

    public String getState() {
        return getColumnElement("state").getText();
    }

    public String getZipCode() {
        return getColumnElement("zipCode").getText();
    }

    public String getCity() {
        return getColumnElement("city").getText();
    }

    public String getRating() {
        return getColumnElement("rating").getText();
    }

    public String getPhone() {
        return getColumnElement("phone.number").getText();
    }

    public void clickEdit() {
        WebElement editButton = this.addressTableRow.findElement(
                By.id("personAddressTableView.searchFreelancer.client[" + this.index + "].edit"));
        editButton.click();
    }

    public void clickRemove() {
        WebElement removeButton = this.addressTableRow.findElement(
                By.id("personAddressTableView.searchFreelancer.client[" + this.index + "].remove"));
        removeButton.click();
    }

    private WebElement getColumnElement(String id) {
        return this.addressTableRow.findElement(By.id("personAddressTableView.searchFreelancer.client[" + this.index + "]." + id));
    }
}
