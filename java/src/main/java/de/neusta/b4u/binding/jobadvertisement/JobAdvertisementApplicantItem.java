package de.neusta.b4u.binding.jobadvertisement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 9/12/17.
 */
public class JobAdvertisementApplicantItem {
    protected final int index;
    protected final WebElement employeeItem;

    public JobAdvertisementApplicantItem(int index, WebElement employeeItem) {
        this.index = index;
        this.employeeItem = employeeItem;
    }

    public String getFirstName() {
        return getColumnElement("firstName").getText().trim();
    }

    public String getLastName() {
        return getColumnElement("lastName").getText().trim();

    }

    public String getCompanyName() {
        return getColumnElement("companyName").getText().trim();
    }

    private WebElement getColumnElement(String id) {
        return this.employeeItem.findElement(By.id("applicantPersonListView.applicantPerson[" + this.index + "]." + id));
    }
}
