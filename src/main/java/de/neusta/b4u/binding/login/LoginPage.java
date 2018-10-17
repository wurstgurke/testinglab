package de.neusta.b4u.binding.login;

import de.neusta.b4u.binding.BasePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by zih on 4/6/17.
 */
public class LoginPage extends BasePage {
    public LoginPage() {
        super("");
    }

    public boolean isDisplayed(WebDriver driver) {
        return SeleniumHelper.elementExists(driver, By.id("loginPage"));
    }

    public void enterUsername(WebDriver driver, String username) {
        WebElement usernameField = driver.findElement(By.id("loginView.userName"));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(WebDriver driver, String password) {
        WebElement passwordField = driver.findElement(By.id("loginView.password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin(WebDriver driver) {
        driver.findElement(By.id("loginView.login")).click();

        try {
            // FIXME: wait for login
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
