package com.example.final_task_epam.model.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MarksValidator {

    public static boolean validateMarks(String[] grades) {
        for (String grade : grades) {
            try {
                int gradeInt = Integer.parseInt(grade);
                if (!(gradeInt >= 1 && gradeInt <= 100)) {

                    return false;
                }
            } catch (NumberFormatException e) {

                return false;
            }
        }
        return true;
    }
}
