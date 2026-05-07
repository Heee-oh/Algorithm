import java.util.*;

class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];
        int[] ans = new int[n];

        // 1. 왼쪽부터 훑으며 최댓값 기록
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        // 2. 오른쪽부터 훑으며 최솟값 기록
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        // 3. 구간을 나누고 각 구간의 최댓값을 정답에 채움
        int start = 0;
        while (start < n) {
            int end = start;
            int currentMax = nums[start];
            
            // P[i] <= S[i+1]이 될 때까지가 하나의 '연결된 그룹'
            while (end < n - 1 && prefixMax[end] > suffixMin[end + 1]) {
                end++;
                currentMax = Math.max(currentMax, nums[end]);
            }
            
            // 찾은 구간 [start, end]를 해당 구간의 최댓값으로 채움
            for (int k = start; k <= end; k++) {
                ans[k] = Math.max(currentMax, nums[k]);
            }
            start = end + 1;
        }

        return ans;
    }
}