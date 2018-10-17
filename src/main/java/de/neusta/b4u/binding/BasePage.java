package de.neusta.b4u.binding;

import de.neusta.b4u.helper.PropertyHelper;
import org.openqa.selenium.WebDriver;

/**
 * Created by zih on 4/6/17.
 */
public abstract class BasePage {
    protected final String pageUrl;

    public BasePage(final String path) {
        pageUrl = PropertyHelper.getCustomProperty("testProperties", "testsystem.baseurl") + path;
    }

    public String getPageUrl() {
        return this.pageUrl;
    }

    public void open(WebDriver driver) {
        driver.get(pageUrl);
    }

    public abstract boolean isDisplayed(WebDriver driver);
}
