package de.neusta.b4u.binding.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 4/20/17.
 */
public class TaskListItem {
    private final int index;
    private final WebElement taskTableRow;

    public TaskListItem(int index, WebElement taskTableRow) {
        this.index = index;
        this.taskTableRow = taskTableRow;
    }

    public int getIndex() {
        return this.index;
    }

    public String getTitle() {
        return getColumnElement("title").getText();
    }

    public String getForPerson() {
        return getColumnElement("person").getText();
    }

    public String getStartDate() {
        return getColumnElement("taskDateStart").getText();
    }

    public String getEndDate() {
        return getColumnElement("taskDateEnd").getText();
    }

    public String getType() {
        return getColumnElement("taskTypeName").getText();
    }

    public String getReminder() {
        return getColumnElement("taskReminderType").getText();
    }

    private WebElement getColumnElement(String id) {
        return this.taskTableRow.findElement(By.id("taskTableView.task[" + this.index + "]." + id));
    }
}
