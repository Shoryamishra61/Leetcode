import java.lang.Math;

class Solution {
    /**
     * Calculates the sum of the n smallest k-mirror numbers.
     * A k-mirror number is a positive integer that is a palindrome in base-10 and base-k.
     *
     * @param k The base to check for palindromicity, in addition to base-10.
     * @param n The number of smallest k-mirror numbers to find.
     * @return The sum of the n smallest k-mirror numbers.
     */
    public long kMirror(int k, int n) {
        long sum = 0;
        int count = 0;
        int len = 1; // Start with palindromes of length 1

        while (count < n) {
            // Determine the range for the first half of the palindrome.
            // For len=5, halfLen=3. The first half ranges from 100 to 999.
            long halfLen = (len + 1) / 2;
            long start = (long) Math.pow(10, halfLen - 1);
            long end = (long) Math.pow(10, halfLen) - 1;

            for (long i = start; i <= end; i++) {
                // Construct the base-10 palindrome from its first half 'i'
                String firstHalf = Long.toString(i);
                StringBuilder secondHalfBuilder = new StringBuilder(firstHalf).reverse();
                
                String palindromeStr;
                if (len % 2 == 1) {
                    // Odd length: e.g., for i=123, palindrome is 12321
                    palindromeStr = firstHalf + secondHalfBuilder.substring(1);
                } else {
                    // Even length: e.g., for i=123, palindrome is 123321
                    palindromeStr = firstHalf + secondHalfBuilder.toString();
                }

                long num = Long.parseLong(palindromeStr);

                // Check if this base-10 palindrome is also a palindrome in base-k
                if (isKPalindrome(num, k)) {
                    sum += num;
                    count++;
                    if (count == n) {
                        return sum;
                    }
                }
            }
            len++;
        }
        return sum;
    }

    /**
     * Checks if a number is a palindrome in a given base.
     *
     * @param num The number to check.
     * @param k The base.
     * @return True if the number's representation in base k is a palindrome, false otherwise.
     */
    private boolean isKPalindrome(long num, int k) {
        // Convert the number to its string representation in base k
        String baseKStr = Long.toString(num, k);
        
        // Check if the resulting string is a palindrome
        int left = 0;
        int right = baseKStr.length() - 1;
        while (left < right) {
            if (baseKStr.charAt(left) != baseKStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}