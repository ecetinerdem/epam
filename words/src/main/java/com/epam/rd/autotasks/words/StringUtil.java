package com.epam.rd.autotasks.words;

import java.util.Arrays;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        if (sample == null || words == null || words.length == 0) {
            return 0;
        }

        String target = sample.trim();
        if (target.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (String word : words) {
            if (word != null && word.trim().equalsIgnoreCase(target)) {
                count++;
            }
        }

        return count;
    }

    public static String[] splitWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }

        String[] words = text.split("[,.;:?! ]+");

        int count = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                count++;
            }
        }

        if (count == 0) {
            return null;
        }

        String[] result = new String[count];
        int index = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                result[index++] = word;
            }
        }

        return result;
    }

    public static String convertPath(String path, boolean toWin) {
        if (path == null || path.isEmpty()) {
            return null;
        }

        // Check for invalid mixed path separators
        if (path.contains("/") && path.contains("\\")) return null;
        if (path.contains("~") && path.contains("\\")) return null;
        if (path.contains(":") && path.contains("/")) return null;

        // Check for ~ not at the beginning or multiple ~ characters
        if (path.contains("~")) {
            if (path.indexOf('~') != 0 || path.lastIndexOf('~') != 0) {
                return null;
            }
        }

        // Check for drive letters - should only appear at the very beginning
        if (path.matches(".*[A-Za-z]:.*")) {
            if (!path.matches("^[A-Za-z]:.*")) {
                return null;
            }
            // Count occurrences of drive letter patterns
            int count = 0;
            for (int i = 0; i < path.length() - 1; i++) {
                char c = path.charAt(i);
                if (Character.isLetter(c) && path.charAt(i + 1) == ':') {
                    count++;
                }
            }
            if (count > 1) {
                return null;
            }
        }

        // Check if path is already in the correct format
        boolean isWindows = path.matches("^[A-Za-z]:.*") || path.startsWith("\\\\");
        boolean isUnix = path.startsWith("/") || path.startsWith("~");

        // Check if path is relative
        boolean isRelative = path.startsWith("./") || path.startsWith("../") ||
                path.equals(".") || path.equals("..") ||
                (!isWindows && !isUnix && !path.isEmpty());

        // If path is already in correct absolute format, return as-is
        if ((toWin && isWindows && !isRelative) || (!toWin && isUnix && !isRelative)) {
            return path;
        }

        // Convert path
        if (toWin) {
            // Unix → Windows
            if (path.startsWith("~")) {
                path = "C:\\User" + path.substring(1).replace("/", "\\");
            } else if (path.startsWith("/")) {
                path = "C:" + path.replace("/", "\\");
            } else {
                // Relative or other paths
                path = path.replace("/", "\\");
            }
        } else {
            // Windows → Unix
            if (path.startsWith("C:\\User")) {
                path = "~" + path.substring("C:\\User".length()).replace("\\", "/");
            } else if (path.matches("^[A-Za-z]:.*")) {
                path = path.substring(2).replace("\\", "/");
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            } else {
                // Relative or other paths
                path = path.replace("\\", "/");
            }
        }

        return path;
    }

    public static String joinWords(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        boolean hasNonEmpty = false;
        boolean first = true;

        for (String word : words) {
            if (word != null && !word.isEmpty()) {
                hasNonEmpty = true;
                if (!first) {
                    sb.append(", ");
                }
                sb.append(word);
                first = false;
            }
        }

        sb.append("]");

        // If no non-empty strings were found
        if (!hasNonEmpty) {
            return null;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS"};
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}