package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpamEmailValidation {

    public static boolean validateEpamEmail(String email) {
        // Handle null input
        if (email == null) {
            return false;
        }

        // Define the pattern for valid EPAM email
        // Pattern breakdown:
        // ^[a-zA-Z]+ - firstname: one or more English letters
        // _ - underscore separator (required)
        // [a-zA-Z]+ - lastname: one or more English letters
        // \d* - optional digits (0 or more) for duplicate handling
        // @epam\.com$ - must end with "@epam.com"
        Pattern pattern = Pattern.compile("^[a-zA-Z]+_[a-zA-Z]+\\d*@epam\\.com$");

        // Use matcher to check if the entire string matches the pattern
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}