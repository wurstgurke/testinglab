package de.neusta.b4u.steps.addresses;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.neusta.b4u.Context;
import de.neusta.b4u.binding.addresses.*;
import de.neusta.b4u.binding.contact.ContactCreatePage;
import de.neusta.b4u.helper.SeleniumHelper;
import org.junit.Assert;

import java.util.List;

/**
 * Created by zih on 5/8/17.
 */
public class AddressEditPersonSteps {
    private AddressEditPersonPage addressEditPersonPage = new AddressEditPersonPage("{id}");

    @Then("^the person details are$")
    public void the_person_details_are(DataTable personData) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        List<AddressCreatePersonPage.ContactType> contactTypes =
                addressEditPersonPage.getContactTypes(Context.getDriver());

        for (List<String> row : personData.raw()) {
            switch (row.get(0)) {
                case "name":
                    Assert.assertEquals("Name",
                            row.get(1), addressEditPersonPage.getName(Context.getDriver()));
                    break;

                case "position":
                    Assert.assertEquals("Position",
                            row.get(1), addressEditPersonPage.getPosition(Context.getDriver()));
                    break;

                case "department":
                    Assert.assertEquals("Department",
                            row.get(1), addressEditPersonPage.getDepartment(Context.getDriver()));
                    break;

                case "phone":
                    Assert.assertEquals("Phone",
                            row.get(1), addressEditPersonPage.getPhone(Context.getDriver()));
                    break;

                case "website":
                    Assert.assertEquals("Website",
                            row.get(1), addressEditPersonPage.getWebsite(Context.getDriver()));
                    break;

                case "email":
                    Assert.assertEquals("E-Mail",
                            row.get(1), addressEditPersonPage.getEmail(Context.getDriver()));
                    break;

                case "birthdate":
                    Assert.assertEquals("Birthdate",
                            row.get(1), addressEditPersonPage.getBirthdate(Context.getDriver()));
                    break;

                case "address":
                    Assert.assertEquals("Address",
                            row.get(1), addressEditPersonPage.getAddress(Context.getDriver()));
                    break;

                case "contacttype":
                    Assert.assertTrue(row.get(1) + " is set",
                            contactTypes.contains(AddressCreatePersonPage.ContactType.getValue(row.get(1))));
                    break;
            }
        }
    }

    @When("^I am on the edit person page of \"([^\"]*)\"")
    public void i_am_on_the_edit_person_page_of(String personName) throws Throwable {
        AddressListPersonPage addressListPersonPage = new AddressListPersonPage();
        addressListPersonPage.open(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        Assert.assertTrue("Person list page is displayed",
                addressListPersonPage.isDisplayed(Context.getDriver()));

        addressListPersonPage.clickClearFilter(Context.getDriver());
        addressListPersonPage.enterNameOrId(Context.getDriver(), personName);
        addressListPersonPage.clickSearch(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<AddressListPersonItem> personItems =
                addressListPersonPage.getSearchResultItems(Context.getDriver(), false);

        AddressListPersonItem personItem = personItems.get(0);
        personItem.clickEdit();

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I edit the person details$")
    public void i_edit_the_person_details(DataTable personData) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.clickEditDetails(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // apply new person data
        AddressStepHelper.applyPersonDetails(addressEditPersonPage, personData.raw());

        // save the new person data
        addressEditPersonPage.clickSaveDetails(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I edit the person contact types")
    public void i_edit_the_person_contact_types(DataTable personData) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.clickEditContactTypes(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // apply new company data
        AddressStepHelper.applyPersonDetails(addressEditPersonPage, personData.raw());

        // save the new company data
        addressEditPersonPage.clickSaveContactTypes(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^I edit the person address$")
    public void i_edit_the_person_address(DataTable personData) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.clickEditAddress(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // apply new company data
        AddressStepHelper.applyPersonDetails(addressEditPersonPage, personData.raw());

        // save the new company data
        addressEditPersonPage.clickSaveAddress(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @And("^I add a new contact \"([^\"]*)\" to the person$")
    public void i_add_a_new_contact_to_the_person(String contactName) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));
        addressEditPersonPage.openContactTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        addressEditPersonPage.clickAddContact(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        ContactCreatePage contactCreatePage = new ContactCreatePage();
        Assert.assertTrue("Contact create page page is displayed",
                contactCreatePage.isDisplayed(Context.getDriver()));

        contactCreatePage.enterName(Context.getDriver(), contactName);
        contactCreatePage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the person contact list should only contain$")
    public void the_person_contact_list_should_only_contain(DataTable expectedContactListTable) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        final List<List<String>> expectedContactList = expectedContactListTable.raw();
        final List<AddressEditContactItem> contactList =
                addressEditPersonPage.getContactList(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Person contact list",
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

    @And("^I add a new know how \"([^\"]*)\"$")
    public void i_add_a_new_know_how(String knowHow) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.openKnowHowTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        addressEditPersonPage.addKnowHow(Context.getDriver(), knowHow);

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I delete the know how \"([^\"]*)\" from the person$")
    public void i_delete_the_know_how_from_the_person(String knowHow) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.openKnowHowTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        addressEditPersonPage.removeKnowHow(Context.getDriver(), knowHow);

        SeleniumHelper.acceptAlert(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the person has the know how \"([^\"]*)\" set$")
    public void the_person_has_the_know_how_set(String knowHow) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.openKnowHowTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<String> knowHows = addressEditPersonPage.getKnowHows(Context.getDriver());
        Assert.assertTrue("Know how is set", knowHows.contains(knowHow));
    }

    @Then("^the person has the know how \"([^\"]*)\" not set$")
    public void the_person_has_the_know_how_not_set(String knowHow) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.openKnowHowTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<String> knowHows = addressEditPersonPage.getKnowHows(Context.getDriver());
        Assert.assertFalse("Know how is not set", knowHows.contains(knowHow));
    }

    @And("^I add a new skill \"([^\"]*)\" to the person$")
    public void i_add_a_new_skill_to_the_person(String skill) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.openSkillTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        addressEditPersonPage.addSkill(Context.getDriver(), skill);

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I delete the skill \"([^\"]*)\" from the person$")
    public void i_delete_the_skill_from_the_person(String skill) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.openSkillTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        addressEditPersonPage.removeSkill(Context.getDriver(), skill);

        SeleniumHelper.acceptAlert(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the person has the skill \"([^\"]*)\" set$")
    public void the_person_has_the_skill_set(String skill) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.openSkillTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<String> skills = addressEditPersonPage.getSkills(Context.getDriver());
        Assert.assertTrue("Skill is set", skills.contains(skill));
    }

    @Then("^the person has the skill \"([^\"]*)\" not set$")
    public void the_person_has_the_skill_not_set(String skill) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.openSkillTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        List<String> skills = addressEditPersonPage.getSkills(Context.getDriver());
        Assert.assertFalse("Skill is not set", skills.contains(skill));
    }

    @And("^I add a new project$")
    public void i_add_a_new_project(DataTable projectData) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.openProjectTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        addressEditPersonPage.clickAddProject(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        AddressEditPersonProjectPage addressEditPersonProjectPage =
                new AddressEditPersonProjectPage();
        AddressStepHelper.applyProjectDetails(addressEditPersonProjectPage, projectData.raw());
        addressEditPersonProjectPage.clickSave(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @When("^I delete the project \"([^\"]*)\"$")
    public void i_delete_the_project(String orderNumber) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.openProjectTab(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        final List<AddressEditPersonProjectItem> projectList =
                addressEditPersonPage.getProjectList(Context.getDriver());

        for (AddressEditPersonProjectItem projectItem : projectList) {
            if (projectItem.getOrderNumber().equals(orderNumber)) {
                projectItem.clickDelete();

                SeleniumHelper.acceptAlert(Context.getDriver());
                SeleniumHelper.waitForAngularFinished(Context.getDriver());

                break;
            }
        }
    }

    @Then("^the person project list should only contain$")
    public void the_person_project_list_should_only_contain(DataTable expectedProjectListTable) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        final List<List<String>> expectedProjectList = expectedProjectListTable.raw();
        final List<AddressEditPersonProjectItem> projectList =
                addressEditPersonPage.getProjectList(Context.getDriver());

        // check if we have enough entries
        Assert.assertEquals("Person project list",
                expectedProjectList.size(), projectList.size());

        // compare each entries
        for (int i = 0;i < expectedProjectList.size();++i) {
            final List<String> expectedProjectItem = expectedProjectList.get(i);
            final AddressEditPersonProjectItem projectItem = projectList.get(i);

            Assert.assertEquals("OrderNumber", expectedProjectItem.get(0), projectItem.getOrderNumber());
            //Assert.assertEquals("ID", expectedProjectItem.get(1), projectItem.getId());
            Assert.assertEquals("Period", expectedProjectItem.get(2), projectItem.getPeriod());
            Assert.assertEquals("CustomerTrade", expectedProjectItem.get(3), projectItem.getCustomerTrade());
            Assert.assertEquals("Position", expectedProjectItem.get(4), projectItem.getPosition());
            Assert.assertEquals("Description", expectedProjectItem.get(5), projectItem.getDescription());
        }
    }

    @Then("^the person project list should be empty$")
    public void the_person_project_list_should_be_empty() throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        final List<AddressEditPersonProjectItem> projectList =
                addressEditPersonPage.getProjectList(Context.getDriver());

        Assert.assertTrue("Person project list is empty", projectList.isEmpty());
    }

    @And("^I edit the person freelancer details$")
    public void i_edit_the_person_freelancer_details(DataTable personData) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        addressEditPersonPage.clickEditFreelancerDetails(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());

        // apply new person data
        AddressStepHelper.applyPersonFreelancerDetails(addressEditPersonPage, personData.raw());

        // save the new person data
        addressEditPersonPage.clickSaveFreelancerDetails(Context.getDriver());

        SeleniumHelper.waitForAngularFinished(Context.getDriver());
    }

    @Then("^the person freelancer details are$")
    public void the_person_freelancer_details_are(DataTable personData) throws Throwable {
        Assert.assertTrue("Person edit page is displayed",
                addressEditPersonPage.isDisplayed(Context.getDriver()));

        for (List<String> row : personData.raw()) {
            switch (row.get(0)) {
                case "cooperationsince":
                    Assert.assertEquals("CooperationSince",
                            row.get(1), addressEditPersonPage.getCooperationSince(Context.getDriver()));
                    break;

                case "availablesince":
                    Assert.assertEquals("AvailableSince",
                            row.get(1), addressEditPersonPage.getAvailableSince(Context.getDriver()));
                    break;

                case "freelancerstate":
                    Assert.assertEquals("Freelancer",
                            row.get(1), addressEditPersonPage.getFreelancerState(Context.getDriver()));
                    break;

                case "hourlyratemin":
                    Assert.assertEquals("HourlyRateMin",
                            row.get(1), addressEditPersonPage.getHourlyRateMin(Context.getDriver()));
                    break;

                case "hourlyratemax":
                    Assert.assertEquals("HourlyRateMax",
                            row.get(1), addressEditPersonPage.getHourlyRateMax(Context.getDriver()));
                    break;

                case "contracttype":
                    Assert.assertEquals("ContractType",
                            row.get(1), addressEditPersonPage.getContractType(Context.getDriver()));
                    break;

                case "rating":
                    Assert.assertEquals("Rating",
                            row.get(1), addressEditPersonPage.getRating(Context.getDriver()));
                    break;
            }
        }
    }
}
