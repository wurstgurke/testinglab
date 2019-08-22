package de.neusta.b4u.steps.login;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.dashboard.DashboardPage;
import de.neusta.b4u.binding.login.LoginPage;
import de.neusta.b4u.helper.PropertyHelper;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.By;

/**
 * Created by zih on 4/6/17.
 */
public class LoginSteps {
    private LoginPage loginPage = new LoginPage();

    @Given("^I have logged into my account as standard user$")
    public void i_have_logged_into_my_account_as_standard_user() throws Throwable {
        final String username = PropertyHelper.getCustomProperty("testProperties", "testdata.standard.username");
        final String password = PropertyHelper.getCustomProperty("testProperties", "testdata.standard.password");

        loginPage.open(Context.getDriver());
        loginPage.enterUsername(Context.getDriver(), username);
        loginPage.enterPassword(Context.getDriver(), password);
        loginPage.clickLogin(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Given("^I have logged into my account as admin user$")
    public void i_have_logged_into_my_account_as_admin() throws Throwable {
        final String username = PropertyHelper.getCustomProperty("testProperties", "testdata.admin.username");
        final String password = PropertyHelper.getCustomProperty("testProperties", "testdata.admin.password");

        loginPage.open(Context.getDriver());
        loginPage.enterUsername(Context.getDriver(), username);
        loginPage.enterPassword(Context.getDriver(), password);
        loginPage.clickLogin(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Given("^I am on the login page$")
    public void i_am_on_the_login_page() throws Throwable {
        loginPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Given("^I provide the standard user credentials$")
    public void i_provide_the_standard_user_credentials() throws Throwable {
        final String username = PropertyHelper.getCustomProperty("testProperties", "testdata.standard.username");
        final String password = PropertyHelper.getCustomProperty("testProperties", "testdata.standard.password");

        loginPage.enterUsername(Context.getDriver(), username);
        loginPage.enterPassword(Context.getDriver(), password);
    }

    @Given("^I provide the admin user credentials$")
    public void i_provide_the_admin_user_credentials() throws Throwable {
        final String username = PropertyHelper.getCustomProperty("testProperties", "testdata.admin.username");
        final String password = PropertyHelper.getCustomProperty("testProperties", "testdata.admin.password");

        loginPage.enterUsername(Context.getDriver(), username);
        loginPage.enterPassword(Context.getDriver(), password);
    }

    @Given("^I provide an invalid username$")
    public void i_provide_an_invalid_username() throws Throwable {
        loginPage.enterUsername(Context.getDriver(), "this_user_should_not_exist");
    }

    @Given("^I provide an invalid password")
    public void i_provide_an_invalid_password() throws Throwable {
        loginPage.enterPassword(Context.getDriver(), "this_password_should_not_exist");
    }

    @When("^I login$")
    public void i_login() throws Throwable {
        loginPage.clickLogin(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I logout$")
    public void i_logout() throws Throwable {
        Context.getDriver().findElement(By.id("topBannerView.logout")).click();
    }

    @Then("^the account page is being displayed$")
    public void the_account_page_is_being_displayed() throws Throwable {
        final DashboardPage dashboardPage = new DashboardPage();

        Assert.assertTrue("Account page is displayed",
                dashboardPage.isDisplayed(Context.getDriver()));
    }

    @Then("^the login page is being displayed$")
    public void the_login_page_is_being_displayed() throws Throwable {
        Assert.assertTrue("Login page is displayed",
                loginPage.isDisplayed(Context.getDriver()));
    }
}
