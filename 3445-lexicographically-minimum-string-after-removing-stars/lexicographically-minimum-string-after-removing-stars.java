import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    /**
     * Removes stars from a string to form the lexicographically smallest result.
     * When a '*' is encountered, it's removed along with the smallest character to its left.
     * If there are ties for the smallest character, the rightmost one is chosen to
     * preserve the prefix and yield a lexicographically smaller string.
     *
     * @param s The input string with lowercase letters and '*'.
     * @return The lexicographically smallest string after all removals.
     */
    public String clearStars(String s) {
        int n = s.length();

        // A list of max-priority queues. Each PQ stores indices for a character.
        // pqs.get(0) for 'a', pqs.get(1) for 'b', etc.
        // Using a max-PQ allows us to get the largest index (rightmost occurrence) efficiently.
        List<PriorityQueue<Integer>> pqs = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            pqs.add(new PriorityQueue<>(Collections.reverseOrder()));
        }

        // A boolean array to mark which characters are removed.
        boolean[] removed = new boolean[n];

        // First pass: Process the string and mark characters for removal.
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                // Find the smallest character that has appeared so far and is not yet removed.
                for (int j = 0; j < 26; j++) {
                    if (!pqs.get(j).isEmpty()) {
                        // This is the smallest character. Get its rightmost index.
                        int indexToRemove = pqs.get(j).poll();
                        // Mark this character as removed.
                        removed[indexToRemove] = true;
                        break; // Stop after removing one character.
                    }
                }
            } else {
                // It's a letter. Add its index to the corresponding PQ.
                pqs.get(c - 'a').add(i);
            }
        }

        // Second pass: Build the resulting string from non-removed characters.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '*' && !removed[i]) {
                result.append(s.charAt(i));
            }
        }

        return result.toString();
    }
}