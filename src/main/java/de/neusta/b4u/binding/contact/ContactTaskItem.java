package de.neusta.b4u.binding.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by zih on 5/4/17.
 */
public class ContactTaskItem {
    private final int index;
    private final WebElement contactTaskRow;

    public ContactTaskItem(int index, WebElement contactTaskRow) {
        this.index = index;
        this.contactTaskRow = contactTaskRow;
    }

    public int getIndex() {
        return this.index;
    }

    public String getType() {
        List<WebElement> elements = getColumnElement(0).findElements(By.tagName("span"));
        return elements.get(0).getText();
    }

    public String getReminder() {
        List<WebElement> elements = getColumnElement(0).findElements(By.tagName("span"));
        return elements.get(1).getText();
    }

    public String getStartDate() {
        List<WebElement> elements = getColumnElement(2).findElements(By.tagName("span"));
        return elements.get(0).getText();
    }

    public String getEndDate() {
        List<WebElement> elements = getColumnElement(2).findElements(By.tagName("span"));
        return elements.get(1).getText().replace("-", "");
    }

    public String getForPerson() {
        return getColumnElement(3).getText();
    }

    public String getNote() {
        return getColumnElement(4).getText();
    }

    public void clickDone() {
        this.contactTaskRow.findElement(By.id("contactTaskView.done")).click();
    }

    public void clickReopen() {
        this.contactTaskRow.findElement(By.id("contactTaskView.reopen")).click();
    }

    public void clickEdit() {
        this.contactTaskRow.findElement(By.id("contactTaskView.editTask")).click();
    }

    public void clickRemove() {
        this.contactTaskRow.findElement(By.id("contactTaskView.removeTask")).click();
    }

    private WebElement getColumnElement(int column) {
        List<WebElement> columnElements = this.contactTaskRow.findElements(By.tagName("td"));
        return columnElements.get(column);
    }
}
