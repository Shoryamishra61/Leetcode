class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // Sort the array to enable two-pointer technique
        Arrays.sort(nums);
        
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2]; // Initialize with first three elements
        
        // Fix the first element and use two pointers for the rest
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // If current sum is closer to target, update closestSum
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                // If we found exact target, return immediately
                if (currentSum == target) {
                    return currentSum;
                }
                // If current sum is less than target, move left pointer right
                else if (currentSum < target) {
                    left++;
                }
                // If current sum is greater than target, move right pointer left
                else {
                    right--;
                }
            }
        }
        
        return closestSum;
    }
}