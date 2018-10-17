package de.neusta.b4u.steps.jobadvertisement;

import de.neusta.b4u.Context;
import de.neusta.b4u.binding.jobadvertisement.JobAdvertisementAddPage;

import java.util.List;

/**
 * Created by zih on 5/10/17.
 */
public class JobAdvertisementStepHelper {
    static void applyJobAdvertisementDetails(JobAdvertisementAddPage jobAdvertisementAddPage, List<List<String>> jobAdvertisementData) {
        for (List<String> row : jobAdvertisementData) {
            switch (row.get(0)) {
                case "name":
                    jobAdvertisementAddPage.enterName(Context.getDriver(), row.get(1));
                    break;

                case "forperson":
                    jobAdvertisementAddPage.selectForPerson(Context.getDriver(), row.get(1));
                    break;

                case "position":
                    jobAdvertisementAddPage.enterPosition(Context.getDriver(), row.get(1));
                    break;

                case "shortdescription":
                    jobAdvertisementAddPage.enterShortDescription(Context.getDriver(), row.get(1));
                    break;

                case "taskdescription":
                    jobAdvertisementAddPage.enterTaskDescription(Context.getDriver(), row.get(1));
                    break;

                case "skills":
                    jobAdvertisementAddPage.enterSkills(Context.getDriver(), row.get(1));
                    break;

                case "notes":
                    jobAdvertisementAddPage.enterNotes(Context.getDriver(), row.get(1));
                    break;

                case "keywords":
                    jobAdvertisementAddPage.enterKeywords(Context.getDriver(), row.get(1));
                    break;

                case "zipcode":
                    jobAdvertisementAddPage.enterZipCode(Context.getDriver(), row.get(1));
                    break;

                case "city":
                    jobAdvertisementAddPage.enterCity(Context.getDriver(), row.get(1));
                    break;

                case "country":
                    jobAdvertisementAddPage.selectCountry(Context.getDriver(), row.get(1));
                    break;

                case "federalstate":
                    jobAdvertisementAddPage.selectFederalState(Context.getDriver(), row.get(1));
                    break;

                case "location":
                    jobAdvertisementAddPage.enterLocation(Context.getDriver(), row.get(1));
                    break;

                case "startdate":
                    jobAdvertisementAddPage.enterStartDate(Context.getDriver(), row.get(1));
                    break;

                case "enddate":
                    jobAdvertisementAddPage.enterEndDate(Context.getDriver(), row.get(1));
                    break;

                case "duration":
                    jobAdvertisementAddPage.enterDuration(Context.getDriver(), row.get(1));
                    break;
            }
        }
    }
}