package de.neusta.b4u.steps.addresses;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.addresses.*;
import de.neusta.b4u.binding.addresses.AddressCreateCompanyPage;
import de.neusta.b4u.binding.contact.ContactCreatePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zih on 5/8/17.
 */
public class AddressEditCompanySteps {
    private AddressEditCompanyPage addressEditCompanyPage = new AddressEditCompanyPage("{id}");

    @Then("^the company details are$")
    public void the_company_details_are(DataTable companyData) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        List<AddressCreateCompanyPage.ContactType> contactTypes =
                addressEditCompanyPage.getContactTypes(Context.getDriver());

        for (List<String> row : companyData.raw()) {
            switch (row.get(0)) {
                case "name":
                    Assert.assertEquals("Name",
                            row.get(1), addressEditCompanyPage.getName(Context.getDriver()));
                    break;

                case "corporation":
                    Assert.assertEquals("Corporation",
                            row.get(1), addressEditCompanyPage.getCorporation(Context.getDriver()));
                    break;

                case "website":
                    Assert.assertEquals("Website",
                            row.get(1), addressEditCompanyPage.getUrl(Context.getDriver()));
                    break;

                case "email":
                    Assert.assertEquals("E-Mail",
                            row.get(1), addressEditCompanyPage.getEmail(Context.getDriver()));
                    break;

                case "loc.address":
                    Assert.assertEquals("Address",
                            row.get(1), addressEditCompanyPage.getLocationAddress(Context.getDriver()));
                    break;

                case "loc.phone":
                    Assert.assertEquals("Phone",
                            row.get(1), addressEditCompanyPage.getLocationPhone(Context.getDriver()));
                    break;

                case "contacttype":
                    Assert.assertTrue(row.get(1) + " is set",
                            contactTypes.contains(AddressCreateCompanyPage.ContactType.getValue(row.get(1))));
                    break;
            }
        }
    }

    @When("^I am on the edit company page of \"([^\"]*)\"")
    public void i_am_on_the_edit_company_page_of(String companyName) throws Throwable {
        AddressListCompanyPage addressListCompanyPage = new AddressListCompanyPage();
        addressListCompanyPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Company list page is displayed",
                addressListCompanyPage.isDisplayed(Context.getDriver()));

        addressListCompanyPage.clickClearFilter(Context.getDriver());
        addressListCompanyPage.enterNameOrId(Context.getDriver(), companyName);
        addressListCompanyPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<AddressListCompanyItem> companyItems =
                addressListCompanyPage.getSearchResultItems(Context.getDriver());

        AddressListCompanyItem companyItem = companyItems.get(0);
        companyItem.clickEdit();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I edit the company details$")
    public void i_edit_the_company_details(DataTable companyData) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        addressEditCompanyPage.clickEditDetails(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // apply new company data
        AddressStepHelper.applyCompanyDetails(addressEditCompanyPage, companyData.raw());

        // save the new company data
        addressEditCompanyPage.clickSaveDetails(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I edit the company contact types")
    public void i_edit_the_company_contact_types(DataTable companyData) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        addressEditCompanyPage.clickEditContactTypes(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // apply new company data
        AddressStepHelper.applyCompanyDetails(addressEditCompanyPage, companyData.raw());

        // save the new company data
        addressEditCompanyPage.clickSaveContactTypes(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^I edit the company location$")
    public void i_edit_the_company_location(DataTable companyData) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        addressEditCompanyPage.clickEditLocation(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // apply new company data
        AddressStepHelper.applyCompanyDetails(addressEditCompanyPage, companyData.raw());

        // save the new company data
        addressEditCompanyPage.clickSaveLocation(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }


    @And("^I add a new contact \"([^\"]*)\" to the company$")
    public void i_add_a_new_contact_to_the_company(String contactName) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));
        addressEditCompanyPage.openContactTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        addressEditCompanyPage.clickAddContact(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        ContactCreatePage contactCreatePage = new ContactCreatePage();
        Assert.assertTrue("Contact create page page is displayed",
                contactCreatePage.isDisplayed(Context.getDriver()));

        contactCreatePage.enterName(Context.getDriver(), contactName);
        contactCreatePage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the company contact list should only contain$")
    public void the_company_contact_list_should_only_contain(DataTable expectedContactListTable) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        final List<List<String>> expectedContactList = expectedContactListTable.raw();
        final List<AddressEditContactItem> contactList =
                addressEditCompanyPage.getContactList(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Company contact list",
                expectedContactList.size(), contactList.size());

        // compare each entries
        for (int i = 0;i < expectedContactList.size();++i) {
            final List<String> desiredContactItem = expectedContactList.get(i);
            final AddressEditContactItem contactItem = contactList.get(i);

            Assert.assertEquals("ID", desiredContactItem.get(0), contactItem.getId());
            Assert.assertEquals("Title", desiredContactItem.get(1), contactItem.getTitle());
            Assert.assertEquals("WithPerson", desiredContactItem.get(2), contactItem.getWithPerson());
            Assert.assertEquals("ForPerson", desiredContactItem.get(3), contactItem.getForPerson());
            //Assert.assertEquals("CreateDate", desiredContactItem.get(4), contactItem.getCreateDate());
            //Assert.assertEquals("LastChange", desiredContactItem.get(5), contactItem.getLastChangeDate());
        }
    }

    @And("^I add a new skill \"([^\"]*)\" to the company$")
    public void i_add_a_new_skill_to_the_company(String skill) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        addressEditCompanyPage.openSkillTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        addressEditCompanyPage.addSkill(Context.getDriver(), skill);

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I delete the skill \"([^\"]*)\" from the company$")
    public void i_delete_the_skill(String skill) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        addressEditCompanyPage.openSkillTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        addressEditCompanyPage.removeSkill(Context.getDriver(), skill);

        SeleniumHelper.acceptAlert(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the company has the skill \"([^\"]*)\" set$")
    public void the_company_has_the_skill_set(String skill) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        addressEditCompanyPage.openSkillTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<String> skills = addressEditCompanyPage.getSkills(Context.getDriver());
        Assert.assertTrue("Skill is set", skills.contains(skill));
    }

    @Then("^the company has the skill \"([^\"]*)\" not set$")
    public void the_company_has_the_skill_not_set(String skill) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        addressEditCompanyPage.openSkillTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<String> skills = addressEditCompanyPage.getSkills(Context.getDriver());
        Assert.assertFalse("Skill is not set", skills.contains(skill));
    }

    @When("^I create a new person \"([^\"]*)\" for the company$")
    public void i_create_a_new_person_for_the_company(String personName, DataTable personData) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        addressEditCompanyPage.clickAddNewPerson(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        final String[] split = personName.split(" ");
        List<String> firstName = Arrays.asList("firstname", split[0]);
        List<String> lastName = Arrays.asList("lastname", split[1]);

        List<List<String>> personDataRaw = new ArrayList<>(personData.raw());
        personDataRaw.add(0, firstName);
        personDataRaw.add(0, lastName);

        AddressCreatePersonPage addressCreatePersonPage = new AddressCreatePersonPage();

        // apply person details
        AddressStepHelper.applyPersonDetails(addressCreatePersonPage, personDataRaw);

        // save the person
        addressCreatePersonPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^I add the person \"([^\"]*)\" to the company$")
    public void i_add_the_person_to_the_company(String personName) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        addressEditCompanyPage.openAddEmployeeList(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        AddressCompanyEmployeePopup addressCompanyEmployeePopup =
                new AddressCompanyEmployeePopup();
        Assert.assertTrue("Add employee popup is displayed",
                addressCompanyEmployeePopup.isDisplayed(Context.getDriver()));

        // search for the name in the employee list
        addressCompanyEmployeePopup.enterName(Context.getDriver(), personName);
        addressCompanyEmployeePopup.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // get first employee item
        List<AddressCompanyEmployeePopupItem> employeeList =
                addressCompanyEmployeePopup.getEmployeeList(Context.getDriver());
        Assert.assertTrue("Employee list is not empty", !employeeList.isEmpty());

        employeeList.get(0).clickAddToCompany();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^the company employee list contains$")
    public void the_company_employee_list_contains(DataTable expectedEmployeeListTable) throws Throwable {
        Assert.assertTrue("Company edit page is displayed",
                addressEditCompanyPage.isDisplayed(Context.getDriver()));

        addressEditCompanyPage.openEmployeeList(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        final List<List<String>> expectedEmployeeList = expectedEmployeeListTable.raw();
        List<AddressCompanyEmployeeItem> employeeList =
                addressEditCompanyPage.getEmployeeList(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Company employee list",
                expectedEmployeeList.size(), employeeList.size());

        // compare each entries
        for (int i = 0;i < expectedEmployeeList.size();++i) {
            final List<String> desiredEmployeeItem = expectedEmployeeList.get(i);
            final AddressCompanyEmployeeItem employeeItem = employeeList.get(i);

            Assert.assertEquals("Name", desiredEmployeeItem.get(0), employeeItem.getName());
            Assert.assertEquals("Position", desiredEmployeeItem.get(1), employeeItem.getPosition());
            Assert.assertEquals("Department", desiredEmployeeItem.get(2), employeeItem.getDepartment());
        }
    }
}
