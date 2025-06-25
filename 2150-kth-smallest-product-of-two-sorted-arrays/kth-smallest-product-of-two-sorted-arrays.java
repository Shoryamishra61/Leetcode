class Solution {
    /**
     * Finds the k-th smallest product of two sorted arrays.
     *
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @param k     The k-th rank (1-based).
     * @return The k-th smallest product.
     */
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long low = -10000000001L;  // A value smaller than the minimum possible product
        long high = 10000000001L; // A value larger than the maximum possible product
        long ans = low;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countLessEqual(mid, nums1, nums2) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    /**
     * Counts the number of products nums1[i] * nums2[j] that are less than or equal to x.
     */
    private long countLessEqual(long x, int[] nums1, int[] nums2) {
        long count = 0;
        for (int num1 : nums1) {
            if (num1 > 0) {
                // If num1 > 0, we need nums2[j] <= x / num1.
                // We count elements in nums2 <= floor(x / num1).
                long target = (long) Math.floor((double) x / num1);
                count += binarySearchLE(nums2, target);
            } else if (num1 < 0) {
                // If num1 < 0, inequality flips: nums2[j] >= x / num1.
                // We count elements in nums2 >= ceil(x / num1).
                long target = (long) Math.ceil((double) x / num1);
                count += (nums2.length - binarySearchGE(nums2, target));
            } else { // num1 == 0
                // Product is 0. If x >= 0, all products are valid (0 <= x).
                if (x >= 0) {
                    count += nums2.length;
                }
            }
        }
        return count;
    }

    /**
     * Counts the number of elements in a sorted array that are less than or equal to a given value.
     * This is equivalent to finding the index of the upper_bound of val.
     *
     * @param arr The sorted array.
     * @param val The value to compare against.
     * @return The count of elements <= val.
     */
    private int binarySearchLE(int[] arr, long val) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= val) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * Finds the index of the first element in a sorted array that is greater than or equal to a given value.
     * This is equivalent to finding the lower_bound of val.
     *
     * @param arr The sorted array.
     * @param val The value to compare against.
     * @return The index of the first element >= val.
     */
    private int binarySearchGE(int[] arr, long val) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= val) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}