package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorCodeValidation {
    public static boolean validateColorCode(String color) {
        // Handle null input
        if (color == null) {
            return false;
        }

        // Define the pattern for valid HTML color codes
        // Must start with # followed by exactly 3 or 6 hexadecimal digits
        Pattern pattern = Pattern.compile("^#([0-9A-Fa-f]{3}|[0-9A-Fa-f]{6})$");

        // Use matcher to check if the entire string matches the pattern
        Matcher matcher = pattern.matcher(color);

        return matcher.matches();
    }
}