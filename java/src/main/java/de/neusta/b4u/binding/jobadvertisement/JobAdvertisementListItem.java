package de.neusta.b4u.binding.jobadvertisement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 4/20/17.
 */
public class JobAdvertisementListItem {
    private final int index;
    private final WebElement advertisementTableRow;

    public JobAdvertisementListItem(int index, WebElement advertisementTableRow) {
        this.index = index;
        this.advertisementTableRow = advertisementTableRow;
    }

    public int getIndex() {
        return this.index;
    }

    public String getCreateDate() {
        return getColumnElement("createDate").getText();
    }

    public String getNumber() {
        return getColumnElement("number").getText().trim();
    }

    public String getPosition() {
        return getColumnElement("position").getText();
    }

    public String getCompany() {
        return getColumnElement("companyName").getText();
    }

    public String getName() {
        return getColumnElement("name").getText();
    }

    public String getPeriod() {
        return getColumnElement("period").getText();
    }

    public String getForPersonName() {
        return getColumnElement("forPersonName").getText();
    }

    public String getCreatePersonName() {
        return getColumnElement("createPersonName").getText();
    }

    public void clickRemove() {
        WebElement removeButton = this.advertisementTableRow.findElement(
                By.id("jobAdvertisementListView.ad[" + this.index + "].remove"));
        removeButton.click();
    }

    private WebElement getColumnElement(String id) {
        return this.advertisementTableRow.findElement(By.id("jobAdvertisementListView.ad[" + this.index + "]." + id));
    }

    public void clickView() {
        getColumnElement("number").click();
    }
}
