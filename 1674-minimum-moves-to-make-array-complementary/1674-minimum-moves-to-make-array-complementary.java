class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[limit * 2 + 2]; // 2 * limit + 1까지 
        // 1 + a ~ b + limit  (1개만 변경)
        // 2(둘 다 1로) ~ 2 * limit (2개 변경)
        // a + b로 딱 맞는 경우

        

        for (int i = 0; i < n/2; i++) {
            int a = Math.min(nums[i], nums[n-1-i]);
            int b = Math.max(nums[i], nums[n-1-i]);

            // 전체 2번이라 가정
            // a,b 둘다 최소 1일때 (2)부터 둘다 limit일때 (2 * limit)
            diff[2] += 2;
            diff[limit * 2 + 1] -= 2;

            // 한 쪽만 변경해도 되는 경우
            diff[a + 1] -= 1;
            diff[b + limit + 1] += 1;

            // a+b로 딱 맞는 경우 
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
        }

        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 2; i <= limit * 2; i++) {
            sum += diff[i];
            ans = Math.min(ans, sum);
        }

        return ans;
    }
}