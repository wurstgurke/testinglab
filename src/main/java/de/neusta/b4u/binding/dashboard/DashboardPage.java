package de.neusta.b4u.binding.dashboard;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by zih on 4/6/17.
 */
public class DashboardPage extends BasePage {
    public DashboardPage() {
        super("");
    }

    public boolean isDisplayed(WebDriver driver) {
        return SeleniumHelper.elementExists(driver, By.id("/dashboard"));
    }
}
