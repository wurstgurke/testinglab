package de.neusta.b4u.binding.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 6/7/17.
 */
public class ContactHistoryItem {
    private final int index;
    private final WebElement contactHistoryRow;

    ContactHistoryItem(int index, WebElement contactHistoryRow) {
        this.index = index;
        this.contactHistoryRow = contactHistoryRow;
    }

    public int getIndex() {
        return this.index;
    }

    public String getTitle() {
        return this.contactHistoryRow.findElement(By.tagName("label")).getText();
    }

    public String getText() {
        return this.contactHistoryRow.findElement(By.className("text-brief")).getText();
    }

    public void select() {
        this.contactHistoryRow.click();
    }
}
