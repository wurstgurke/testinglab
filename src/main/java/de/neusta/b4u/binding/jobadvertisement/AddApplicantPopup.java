package de.neusta.b4u.binding.jobadvertisement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zih on 9/12/17.
 */
public class AddApplicantPopup {
    public boolean isDisplayed(WebDriver driver) {
        return !driver.findElements(By.id("addApplicantPersonView.table")).isEmpty();
    }

    public void enterName(WebDriver driver, String name) {
        driver.findElement(By.id("addApplicantPersonView.name")).sendKeys(name);
    }

    public void enterEmail(WebDriver driver, String email) {
        driver.findElement(By.id("addApplicantPersonView.emailAddress")).sendKeys(email);
    }

    public void clickSearch(WebDriver driver) {
        driver.findElement(By.id("addApplicantPersonView.search1")).click();
    }

    public void clickClearFilter(WebDriver driver) {
        driver.findElement(By.id("addApplicantPersonView.clear")).click();
    }

    public List<AddApplicantPopupItem> getApplicantList(WebDriver driver) {
        WebElement employeeListTableView = driver.findElement(By.id("addApplicantPersonView.table"));

        List<AddApplicantPopupItem> applicantList = new ArrayList<>();

        List<WebElement> applicantListTableRows = employeeListTableView.findElements(By.cssSelector("tbody>tr"));
        for (int i = 0; i < applicantListTableRows.size();++i) {
            AddApplicantPopupItem applicantPopupItem =
                    new AddApplicantPopupItem(i, applicantListTableRows.get(i));

            applicantList.add(applicantPopupItem);
        }

        return applicantList;
    }
}
