package de.neusta.b4u.binding.jobadvertisement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 9/12/17.
 */
public class AddApplicantPopupItem {
    protected final int index;
    protected final WebElement applicantPopupItem;

    public AddApplicantPopupItem(int index, WebElement applicantPopupItem) {
        this.index = index;
        this.applicantPopupItem = applicantPopupItem;
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

    public void clickAddToJobAdvertisement() {
        this.applicantPopupItem.click();
    }

    private WebElement getColumnElement(int index) {
        return this.applicantPopupItem.findElements(By.tagName("td")).get(index);
    }
}
