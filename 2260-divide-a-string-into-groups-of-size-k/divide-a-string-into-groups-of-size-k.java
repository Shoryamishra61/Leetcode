import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * Divides a string into groups of size k, padding the last group if necessary.
     *
     * @param s The input string to be divided.
     * @param k The size of each group.
     * @param fill The character to use for padding the last group.
     * @return An array of strings representing the groups.
     */
    public String[] divideString(String s, int k, char fill) {
        // Get the length of the input string.
        int n = s.length();
        
        // Use a StringBuilder for efficient string concatenation.
        StringBuilder paddedString = new StringBuilder(s);
        
        // Calculate how many characters are left over after forming full groups.
        int remainder = n % k;
        
        // If there is a remainder, it means the last group is incomplete.
        if (remainder != 0) {
            // Calculate the number of 'fill' characters needed to complete the last group.
            int charsToFill = k - remainder;
            // Append the required number of 'fill' characters.
            for (int i = 0; i < charsToFill; i++) {
                paddedString.append(fill);
            }
        }
        
        // After padding, the string length is a multiple of k.
        // Calculate the total number of groups.
        int numGroups = paddedString.length() / k;
        String[] result = new String[numGroups];
        
        // Iterate through the padded string and slice it into groups of size k.
        for (int i = 0; i < numGroups; i++) {
            int startIndex = i * k;
            int endIndex = startIndex + k;
            result[i] = paddedString.substring(startIndex, endIndex);
        }
        
        // Return the final array of string groups.
        return result;
    }
}