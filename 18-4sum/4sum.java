import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // Initialize the list to store the results.
        List<List<Integer>> result = new ArrayList<>();
        // If the array has fewer than 4 elements, no quadruplet is possible.
        if (nums == null || nums.length < 4) {
            return result;
        }

        // Sort the array. This is key to using the two-pointer approach and skipping duplicates.
        Arrays.sort(nums);
        int n = nums.length;

        // Outer loop for the first element of the quadruplet.
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicate values for the first element to avoid duplicate quadruplets.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Inner loop for the second element of the quadruplet.
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicate values for the second element.
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // Use the two-pointer approach for the remaining two elements.
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    // Use long to prevent potential integer overflow when summing up.
                    long currentSum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (currentSum == target) {
                        // Found a valid quadruplet.
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // Skip duplicate values for the third element.
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // Skip duplicate values for the fourth element.
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        // Move pointers to find the next unique pair.
                        left++;
                        right--;
                    } else if (currentSum < target) {
                        // The sum is too small, need a larger number.
                        left++;
                    } else {
                        // The sum is too large, need a smaller number.
                        right--;
                    }
                }
            }
        }
        return result;
    }
}