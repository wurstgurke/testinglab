package de.neusta.b4u.steps.tasks;

import de.neusta.b4u.Context;
import de.neusta.b4u.binding.tasks.TaskPopupPage;

import java.util.List;

/**
 * Created by zih on 5/8/17.
 */
public class TaskStepHelper {
    static void applyTaskDetails(TaskPopupPage addTaskPage, List<List<String>> taskData) {
        for (List<String> row : taskData) {
            switch (row.get(0)) {
                case "type":
                    addTaskPage.selectTaskType(Context.getDriver(), row.get(1));
                    break;

                case "reminder":
                    addTaskPage.selectReminder(Context.getDriver(), row.get(1));
                    break;

                case "startdate":
                    addTaskPage.enterStartDate(Context.getDriver(), row.get(1));
                    break;

                case "enddate":
                    addTaskPage.enterEndDate(Context.getDriver(), row.get(1));
                    break;

                case "forperson":
                    addTaskPage.enterForPerson(Context.getDriver(), row.get(1));
                    break;

                case "note":
                    addTaskPage.enterNote(Context.getDriver(), row.get(1));
                    break;
            }
        }
    }
}
