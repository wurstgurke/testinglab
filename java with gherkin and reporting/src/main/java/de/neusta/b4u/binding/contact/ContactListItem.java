package de.neusta.b4u.binding.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 4/20/17.
 */
public class ContactListItem {
    private final int index;
    private final WebElement contactTableRow;

    public ContactListItem(int index, WebElement contactTableRow) {
        this.index = index;
        this.contactTableRow = contactTableRow;
    }

    public int getIndex() {
        return this.index;
    }

    public String getId() {
        String href = getColumnElement("title").getAttribute("cell-href");
        return href.substring(href.lastIndexOf("/")+1);
    }

    public String getTitle() {
        return getColumnElement("title").getText();
    }

    public String getForPerson() {
        return getColumnElement("forPerson").getText();
    }

    public String getWithPerson() {
        return getColumnElement("withPerson").getText();
    }

    public String getWithCompany() {
        return getColumnElement("withCompany").getText();
    }

    public void clickEdit() {
        WebElement editButton = getColumnElement("title");
        editButton.click();
    }

    public void clickRemove() {
        WebElement removeButton = this.contactTableRow.findElement(
                By.id("contactTableView.contact[" + this.index + "].remove"));
        removeButton.click();
    }

    private WebElement getColumnElement(String id) {
        return this.contactTableRow.findElement(By.id("contactTableView.contact[" + this.index + "]." + id));
    }
}
