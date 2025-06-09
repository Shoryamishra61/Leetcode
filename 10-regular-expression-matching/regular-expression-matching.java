class Solution {
    /**
     * Implements regular expression matching with support for '.' and '*'.
     *
     * @param s The input string (contains only lowercase English letters).
     * @param p The pattern (contains only lowercase English letters, '.', and '*').
     * @return true if the pattern matches the entire string, false otherwise.
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];

        // Base case: an empty string matches an empty pattern.
        dp[0][0] = true;

        // Deals with patterns like "a*", "a*b*", ".*" matching an empty string.
        for (int j = 1; j <= pLen; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // Fill the DP table.
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);

                if (pChar == '.' || pChar == sChar) {
                    // Case 1: Direct match or '.' wildcard.
                    // The result depends on the match of the previous substrings.
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pChar == '*') {
                    // Case 2: '*' wildcard.
                    // Option A: '*' matches zero of the preceding element.
                    // We look at the pattern two steps back, ignoring the 'x*'
                    dp[i][j] = dp[i][j - 2];

                    // Option B: '*' matches one or more of the preceding element.
                    // This is only possible if the current string char matches the char before '*'.
                    char pPrevChar = p.charAt(j - 2);
                    if (pPrevChar == '.' || pPrevChar == sChar) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
                // else, dp[i][j] remains false.
            }
        }

        return dp[sLen][pLen];
    }
}