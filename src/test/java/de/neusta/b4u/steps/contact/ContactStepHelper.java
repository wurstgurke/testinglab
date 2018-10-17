package de.neusta.b4u.steps.contact;

import de.neusta.b4u.Context;
import de.neusta.b4u.binding.contact.ContactCreatePage;

import java.util.List;

/**
 * Created by zih on 5/8/17.
 */
public class ContactStepHelper {
    static void applyContactDetails(ContactCreatePage contactCreatePage, List<List<String>> contactData) {
        for (List<String> row : contactData) {
            switch (row.get(0)) {
                case "name":
                    contactCreatePage.enterName(Context.getDriver(), row.get(1));
                    break;

                case "forperson":
                    contactCreatePage.enterForPerson(Context.getDriver(), row.get(1));
                    break;

                case "withperson":
                    contactCreatePage.enterWithPerson(Context.getDriver(), row.get(1));
                    break;

                case "withcompany":
                    contactCreatePage.enterWithCompany(Context.getDriver(), row.get(1));
                    break;
            }
        }
    }
}
