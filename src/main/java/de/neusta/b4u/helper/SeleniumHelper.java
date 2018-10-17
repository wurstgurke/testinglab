package de.neusta.b4u.helper;

import de.neusta.b4u.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by malpen on 14.02.17.
 */
public class SeleniumHelper extends Base {
    public static Boolean elementExists(WebDriver driver, By by) {
        return !driver.findElements(by).isEmpty();
    }

    public static void selectFromDropdown(WebDriver driver, By by, String selection) {
        selectFromDropdown(driver, by, selection, false);
    }

    public static void selectFromDropdown(WebDriver driver, By by, String selection, boolean findLast) {
        WebElement dropdownMenu = null;
        if (findLast) {
            dropdownMenu = SeleniumHelper.findLastElement(driver, by);
        } else {
            dropdownMenu = driver.findElement(by);
        }

        selectFromDropdown(driver, dropdownMenu, selection);
    }

    public static void selectFromDropdown(WebDriver driver, WebElement dropdownMenu, String selection) {
        dropdownMenu.click();

        waitForAngularFinished(driver);

        List<WebElement> elementList = dropdownMenu.findElements(By.className("ui-select-choices-row"));
        for (WebElement element : elementList) {
            if (element.getText().equalsIgnoreCase(selection)) {
                element.click();
                break;
            }
        }
    }

    private static WebElement findLastElement(WebDriver driver, By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.get(elements.size()-1);
    }

    public static void acceptAlert(WebDriver driver) {
        // Wait 10 seconds till alert is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Accepting alert.
        alert.accept();
    }

    public static void waitForAngularFinished(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 100);
        wait.until(angularHasFinishedProcessing());

        try {
            Thread.sleep(350);
            //Thread.sleep(150);
        } catch (Exception e) {
            // NOTHING
        }
    }

    private static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return Boolean.valueOf(((JavascriptExecutor) driver).executeScript("return (angular.element(document.body).injector().get('$http').pendingRequests.length === 0)").toString());
            }
        };
    }
}
