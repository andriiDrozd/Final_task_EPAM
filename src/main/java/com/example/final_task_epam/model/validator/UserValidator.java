package com.example.final_task_epam.model.validator;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    private final static int VALIDATION_PARAMETERS_NUMBER = 7;

    private static final int EMAIL_ERROR_INDEX = 0;
    private static final int NAME_ERROR_INDEX = 1;
    private static final int SURNAME_ERROR_INDEX = 2;
    private static final int REGION_ERROR_INDEX = 3;
    private static final int CITY_ERROR_INDEX = 4;
    private static final int INSTITUTE_ERROR_INDEX = 5;

    private static final int PASSWORD_ERROR_INDEX = 6;
    private static final int PASSWORDS_REPEAT_ERROR_INDEX = 7;


    private static final String NAME_PATTERN = "([A-Z][a-z]{1,20})|([А-Я][а-я]{1,20})";
    private static final String PASSWORD_PATTERN ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String INSTITUTE_PATTERN = "([\"A-Z\"][\"a-z\"]+|[\"a-z\"])|([\"А-Я\"][\"а-я\"]+|[\"а-я\"])";

    private static final String[] ERROR_MESSAGES;


    static {
        ERROR_MESSAGES = new String[VALIDATION_PARAMETERS_NUMBER];
        ERROR_MESSAGES[EMAIL_ERROR_INDEX] = "Invalid email";
        ERROR_MESSAGES[NAME_ERROR_INDEX] = "Invalid name - Name must start with a capital letter";
        ERROR_MESSAGES[SURNAME_ERROR_INDEX] = "Invalid surname - Surname must start with a capital letter";
        ERROR_MESSAGES[REGION_ERROR_INDEX] = "Invalid region - Region must start with a capital letter";
        ERROR_MESSAGES[CITY_ERROR_INDEX] = "Invalid city - City must start with a capital letter";
        ERROR_MESSAGES[INSTITUTE_ERROR_INDEX]="Invalid Institute name - Fill FULL name of the educational institution";
        ERROR_MESSAGES[PASSWORD_ERROR_INDEX] = "Invalid password - Minimum eight characters, at least one uppercase letter, one lowercase letter and one number:";


    }


    public static List<String> validateRegistrationForm(String email, String password,  String name,
                                                        String surname, String region, String city, String institute ) {

        boolean[] checks = new boolean[VALIDATION_PARAMETERS_NUMBER];
        checks[EMAIL_ERROR_INDEX] = UserValidator.validateEmail(email);
        checks[NAME_ERROR_INDEX] = UserValidator.validateNameComponent(name);
        checks[SURNAME_ERROR_INDEX] = UserValidator.validateNameComponent(surname);
        checks[REGION_ERROR_INDEX] = UserValidator.validateNameComponent(region);
        checks[CITY_ERROR_INDEX]=UserValidator.validateNameComponent(city);
        checks[INSTITUTE_ERROR_INDEX] = UserValidator.validateInstitute(institute);
        checks[PASSWORD_ERROR_INDEX] = UserValidator.validatePassword(password);



        return createErrorList(checks);
    }

    private static List<String> createErrorList(boolean[] checks) {

        List<String> errors = new ArrayList<>(VALIDATION_PARAMETERS_NUMBER);

        int errorNumber = 0;
        for (int i = 0; i < VALIDATION_PARAMETERS_NUMBER; i++) {
            if (!checks[i]) {
//                LOGGER.info(ERROR_MESSAGES[i]);
                errors.add(ERROR_MESSAGES[i]);
                errorNumber++;
            } else {
                errors.add(null);
            }
        }

        return errorNumber == 0 ? null : errors;
    }

//    public static List<String> validateEditingForm(String lastName, String firstName, String patronymic) {
//
//        boolean[] checks = new boolean[VALIDATION_PARAMETERS_NUMBER];
//        checks[LOGIN_ERROR_INDEX] = true;
//        checks[PASSWORD_ERROR_INDEX] = true;
//        checks[PASSWORDS_DIFFER_ERROR_INDEX] = true;
//        checks[SURNAME_ERROR_INDEX] = UserDataValidator.validateNameComponent(lastName);
//        checks[NAME_ERROR_INDEX] = UserDataValidator.validateNameComponent(firstName);
//        checks[PATRONYMIC_ERROR_INDEX] = UserDataValidator.validateNameComponent(patronymic);
//        checks[USER_ROLE_ERROR_INDEX] = true;
//        return createErrorList(checks);
//    }

    private static boolean validateInstitute(String institute) {
        if (institute != null) {
            return institute.matches(INSTITUTE_PATTERN);
        } else {
            return false;
        }
    }

    private static boolean validateNameComponent(String string) {
        if (string != null) {
            return string.matches(NAME_PATTERN);
        } else {
            return false;
        }
    }

    private static boolean validateEmail(String email) {
        if (email != null) {
            return email.matches(EMAIL_PATTERN);
        } else {
            return false;
        }
    }


    private static boolean validatePassword(String string) {
        if (string != null) {
            return string.matches(PASSWORD_PATTERN);
        } else {
            return false;
        }
    }

    private static boolean validateRepeatPassword(String string, String repeat) {
        if (string != null && repeat != null) {
            return string.equals(repeat);
        } else {
            return false;
        }
    }

}
