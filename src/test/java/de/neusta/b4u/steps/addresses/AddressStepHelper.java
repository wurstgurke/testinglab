package de.neusta.b4u.steps.addresses;

import de.neusta.b4u.Context;
import de.neusta.b4u.binding.addresses.AddressCreateCompanyPage;
import de.neusta.b4u.binding.addresses.AddressCreatePersonPage;
import de.neusta.b4u.binding.addresses.AddressEditPersonPage;
import de.neusta.b4u.binding.addresses.AddressEditPersonProjectPage;
import org.junit.Assert;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by zih on 5/8/17.
 */
class AddressStepHelper {
    static void applyCompanyDetails(AddressCreateCompanyPage addressCreateCompanyPage, List<List<String>> companyData) {
        for (List<String> row : companyData) {
            switch (row.get(0)) {
                case "name":
                    addressCreateCompanyPage.enterName(Context.getDriver(), row.get(1));
                    break;

                case "corporation":
                    addressCreateCompanyPage.enterCorporation(Context.getDriver(), row.get(1));
                    break;

                case "website":
                    addressCreateCompanyPage.enterUrl(Context.getDriver(), row.get(1));
                    break;

                case "email":
                    addressCreateCompanyPage.enterEmail(Context.getDriver(), row.get(1));
                    break;

                case "loc.name":
                    addressCreateCompanyPage.enterNewLocationName(Context.getDriver(), row.get(1));
                    break;

                case "loc.street":
                    addressCreateCompanyPage.enterNewLocationStreet(Context.getDriver(), row.get(1));
                    break;

                case "loc.zipcode":
                    addressCreateCompanyPage.enterNewLocationZipCode(Context.getDriver(), row.get(1));
                    break;

                case "loc.city":
                    addressCreateCompanyPage.enterNewLocationCity(Context.getDriver(), row.get(1));
                    break;

                case "loc.country":
                    addressCreateCompanyPage.selectNewLocationCountry(Context.getDriver(), row.get(1));
                    break;

                case "loc.phone":
                    addressCreateCompanyPage.enterNewLocationPhone(Context.getDriver(), row.get(1));
                    break;

                case "contacttype":
                    addressCreateCompanyPage.selectContactType(Context.getDriver(),
                            AddressCreateCompanyPage.ContactType.getValue(row.get(1)));
                    break;
            }
        }
    }

    static void applyPersonDetails(AddressCreatePersonPage addressCreatePersonPage, List<List<String>> personData) {
        for (List<String> row : personData) {
            switch (row.get(0)) {
                case "salutation":
                    addressCreatePersonPage.selectSalutation(Context.getDriver(), row.get(1));
                    break;

                case "firstname":
                    addressCreatePersonPage.enterFirstName(Context.getDriver(), row.get(1));
                    break;

                case "lastname":
                    addressCreatePersonPage.enterLastName(Context.getDriver(), row.get(1));
                    break;

                case "position":
                    addressCreatePersonPage.enterPosition(Context.getDriver(), row.get(1));
                    break;

                case "department":
                    addressCreatePersonPage.enterDepartment(Context.getDriver(), row.get(1));
                    break;

                case "phone":
                    addressCreatePersonPage.enterPhone(Context.getDriver(), row.get(1));
                    break;

                case "website":
                    addressCreatePersonPage.enterWebsite(Context.getDriver(), row.get(1));
                    break;

                case "email":
                    addressCreatePersonPage.enterEmail(Context.getDriver(), row.get(1));
                    break;

                case "birthdate":
                    addressCreatePersonPage.enterBirthdate(Context.getDriver(), row.get(1));
                    break;

                case "street":
                    addressCreatePersonPage.enterStreet(Context.getDriver(), row.get(1));
                    break;

                case "zipcode":
                    addressCreatePersonPage.enterZipCode(Context.getDriver(), row.get(1));
                    break;

                case "city":
                    addressCreatePersonPage.enterCity(Context.getDriver(), row.get(1));
                    break;

                case "country":
                    addressCreatePersonPage.selectCountry(Context.getDriver(), row.get(1));
                    break;

                case "addresstype":
                    addressCreatePersonPage.selectAddressType(Context.getDriver(), row.get(1));
                    break;

                case "contacttype":
                    addressCreatePersonPage.selectContactType(Context.getDriver(),
                            AddressCreatePersonPage.ContactType.getValue(row.get(1)));
                    break;
            }
        }
    }

    static void applyPersonFreelancerDetails(AddressEditPersonPage addressEditPersonPage, List<List<String>> personData) {
        for (List<String> row : personData) {
            switch (row.get(0)) {
                case "cooperationsince":
                    addressEditPersonPage.enterCooperationSince(Context.getDriver(), row.get(1));
                    break;

                case "availablesince":
                    addressEditPersonPage.enterAvailableSince(Context.getDriver(), row.get(1));
                    break;

                case "freelancerstate":
                    addressEditPersonPage.selectFreelancerState(Context.getDriver(), row.get(1));
                    break;

                case "hourlyratemin":
                    addressEditPersonPage.enterHourlyRateMin(Context.getDriver(), row.get(1));
                    break;

                case "hourlyratemax":
                    addressEditPersonPage.enterHourlyRateMax(Context.getDriver(), row.get(1));
                    break;

                case "contracttype":
                    addressEditPersonPage.selectContractType(Context.getDriver(), row.get(1));
                    break;

                case "rating":
                    addressEditPersonPage.selectRating(Context.getDriver(), row.get(1));
                    break;
            }
        }
    }

    static void applyProjectDetails(AddressEditPersonProjectPage addressEditPersonProjectPage, List<List<String>> projectData) {
        for (List<String> row : projectData) {
            switch (row.get(0)) {
                case "ordernumber":
                    addressEditPersonProjectPage.enterOrderNumber(Context.getDriver(), row.get(1));
                    break;

                case "startdate":
                    addressEditPersonProjectPage.enterStartDate(Context.getDriver(), row.get(1));
                    break;

                case "enddate":
                    addressEditPersonProjectPage.enterEndDate(Context.getDriver(), row.get(1));
                    break;

                case "position":
                    addressEditPersonProjectPage.enterPosition(Context.getDriver(), row.get(1));
                    break;

                case "customertrade":
                    addressEditPersonProjectPage.enterCustomerTrade(Context.getDriver(), row.get(1));
                    break;

                case "description":
                    addressEditPersonProjectPage.enterDescription(Context.getDriver(), row.get(1));
                    break;

                case "technical":
                    addressEditPersonProjectPage.enterTechnicalDescription(Context.getDriver(), row.get(1));
                    break;
            }
        }
    }
}
