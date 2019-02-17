package de.neusta.b4u.binding.addresses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 6/21/17.
 */
public class AddressEditPersonProjectItem {
    protected final int index;
    protected final WebElement projectListTableRow;

    public AddressEditPersonProjectItem(int index, WebElement projectListTableRow) {
        this.index = index;
        this.projectListTableRow = projectListTableRow;
    }

    public int getIndex() {
        return this.index;
    }

    public String getOrderNumber() {
        return getColumnElement(0).getAttribute("innerText").trim();
    }

    public String getId() {
        return getColumnElement(1).getAttribute("innerText").trim();
    }

    public String getPeriod() {
        return getColumnElement(2).getAttribute("innerText").trim().replace("\n", " - ");
    }

    public String getCustomerTrade() {
        return getColumnElement(3).getAttribute("innerText").trim();
    }

    public String getPosition() {
        return getColumnElement(4).getAttribute("innerText").trim();
    }

    public String getDescription() {
        return getColumnElement(5).getAttribute("innerText").trim();
    }

    public void clickDelete() {
        this.projectListTableRow.findElement(By.id("personProjectsView.project[" + this.index + "].delete")).click();
    }

    private WebElement getColumnElement(int index) {
        return this.projectListTableRow.findElements(By.tagName("td")).get(index);
    }
}
