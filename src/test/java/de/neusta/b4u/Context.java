package de.neusta.b4u;

import org.openqa.selenium.WebDriver;

/**
 * Created by zih on 4/6/17.
 */
public class Context {
    private final WebDriver driver;
    private static Context instance;

    public static void init(WebDriver driver) {
        instance = new Context(driver);
    }

    public static void clear() {
        instance = null;
    }

    public static WebDriver getDriver() {
        return instance.driver;
    }

    private Context(WebDriver driver) {
        this.driver = driver;
    }
}
