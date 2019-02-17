package de.neusta.b4u;

import de.neusta.b4u.helper.PropertyHelper;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by malpen on 14.02.17.
 */
public class Base {
    private WebDriver driver = null;
    private static Boolean alreadyUp = false;
    private static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    private static DesiredCapabilities getBrowserCapabilities(String browserName){
        switch (browserName) {
            case "chrome":
                desiredCapabilities = DesiredCapabilities.chrome();
                break;

            case "firefox":
                desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setCapability("marionette", true);
                break;

            case "phantomjs":
                desiredCapabilities = DesiredCapabilities.phantomjs();
                break;
        }
        return desiredCapabilities;
    }

    public static DesiredCapabilities getCapabilities(String value) {
        desiredCapabilities.setCapability(
                PropertyHelper.getCustomProperty("testProperties", "desired.capabilities.ssl.name"),
                PropertyHelper.getCustomProperty("testProperties", "desired.capabilities.ssl.value")
        );
        desiredCapabilities.setCapability(
                PropertyHelper.getCustomProperty("testProperties", "desired.capabilities.browser.name"),
                PropertyHelper.getCustomProperty("testProperties", "desired.capabilities.browser.value")
        );
        desiredCapabilities.setCapability(
                PropertyHelper.getCustomProperty("testProperties", "desired.capabilities.os.name"),
                PropertyHelper.getCustomProperty("testProperties", "desired.capabilities.os.value")
        );
        return desiredCapabilities;
    }

    public WebDriver initialStepsWithBaseUrl(WebDriver driver, String currentBaseUrl) throws Exception {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver_linux");
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability("nativeElements", false);
        try {
            driver = new ChromeDriver();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        driver.get(currentBaseUrl);
        return driver;
    }

    public WebDriver getDriver(String browser) throws MalformedURLException {
        if (!alreadyUp) {
            final String os = System.getProperty("os.name");

            // setup web driver
            switch (os) {
                case "Linux":
                    System.setProperty("webdriver.chrome.driver", "driver/chromedriver_linux");
                    System.setProperty("webdriver.gecko.driver", "driver/geckodriver_linux");
                    break;

                case "Mac OS X":
                    System.setProperty("webdriver.chrome.driver", "driver/chromedriver_mac");
                    System.setProperty("webdriver.gecko.driver", "driver/geckodriver_mac");
                    break;

                default:
                    System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
                    System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
                    break;
            }

            boolean seleniumGridEnabled = Boolean.parseBoolean(
                    PropertyHelper.getCustomProperty("testProperties", "selenium.grid.enabled"));

            if (!seleniumGridEnabled) {
                switch (browser) {
                    case "chrome":
                        driver = new ChromeDriver();
                        break;

                    case "firefox":
                        driver = new FirefoxDriver();
                        break;

                    default:
                        throw new IllegalArgumentException("Undefined browser");
                }
            } else {
                driver = new RemoteWebDriver(
                        new URL(PropertyHelper.getCustomProperty("testProperties", "selenium.grid.baseurl")),
                        getBrowserCapabilities(browser));
            }

            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

            // set the large windows size because selenium is too f***ing dumb to move elements into the view
            driver.manage().window().setSize(new Dimension(3000, 3000));

            alreadyUp = true;
        }

        return driver;
    }

    protected void quitDriver(WebDriver driver) {
        if (alreadyUp) {
            driver.quit();

            alreadyUp = false;
        }
    }
}
