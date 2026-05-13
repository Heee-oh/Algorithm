class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        // 합이 2부터 2 * limit까지 가능
        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);

            // 1. 기본적으로 모든 합 S에 대해 2번 이동이 필요하다고 가정
            // [2, 2 * limit] 구간에 +2
            diff[2] += 2;
            diff[2 * limit + 1] -= 2; // 누적합이므로 2*limit + 1에는 -2

            // 2. 1번만 이동해도 되는 구간 [a + 1, b + limit]은 비용을 1 깎아줌
            // 결과적으로 이 구간은 2 - 1 = 1이 됨
            diff[a + 1] -= 1;
            diff[b + limit + 1] += 1;

            // 3. 0번 이동해도 되는 지점 (a + b)은 비용을 한 번 더 깎아줌
            // 결과적으로 이 지점은 1 - 1 = 0이 됨
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
        }

        System.out.println(Arrays.toString(diff));
        int minMoves = n; // 최대 이동 횟수는 n번 (모든 숫자를 다 바꾸는 경우)
        int currentMoves = 0;

        // 차분 배열을 훑으며(누적합) 실제 이동 횟수를 계산
        for (int i = 2; i <= 2 * limit; i++) {
            currentMoves += diff[i];
            minMoves = Math.min(minMoves, currentMoves);
        }

        return minMoves;
    }
}