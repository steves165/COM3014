package group1.service;

/**
 * Static class to help with parsing integers from other formats.
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public class IntegerHelper {
    /**
     * Try to parse the string into an integer, if the operation fails null is
     * returned.
     * @param str String to try and parse into an integer (base 10)
     * @return The string as an integer or null if the string could not be parsed
     */
    public static Integer tryParse(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // Ignore the exception and return null
        }
        return null;
    }
}
