class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int s = 0, e = n - 1;
        int target = nums[0];   
        int idx = 0;
        while (s <= e) {
            int mid = (s + e) >>> 1;


            if (target <= nums[mid]) {
                s = mid + 1;
                
            } else {
                idx = mid;
                e = mid - 1;
            }

            target = nums[mid];
        }

        return nums[idx];
    }
}