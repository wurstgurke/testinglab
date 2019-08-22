package de.neusta.b4u.binding.addresses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 6/21/17.
 */
public class AddressEditContactItem {
    protected final int index;
    protected final WebElement contactListTableRow;

    public AddressEditContactItem(int index, WebElement contactListTableRow) {
        this.index = index;
        this.contactListTableRow = contactListTableRow;
    }

    public int getIndex() {
        return this.index;
    }

    public String getId() {
        return getColumnElement("id").getText();
    }

    public String getTitle() {
        return getColumnElement("title").getText();
    }

    public String getWithPerson() {
        return getColumnElement("withPersonId").getText();
    }

    public String getForPerson() {
        return getColumnElement("forPersonId").getText();
    }

    public String getCreateDate() {
        return getColumnElement("createDate").getText();
    }

    public String getLastChangeDate() {
        return getColumnElement("lastChange").getText();
    }

    private WebElement getColumnElement(String id) {
        return this.contactListTableRow.findElement(By.id("contactListTableView.contact[" + this.index + "]." + id));
    }
}
