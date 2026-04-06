import java.util.function.Function;

class Solution {
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        int n = depth.length;

        // 1-indexed로 사용
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = depth[i - 1];
        }

        // dp[l][r] = [l, r] 구간에서 보물을 반드시 찾기 위한 최소 보장 비용
        long[][] dp = new long[n + 2][n + 2]; 
        int[][] choice = new int[n + 2][n + 2]; // choice[l][r] 구간에서 최소 비용인 위치

        // 길이 1부터 n까지
        for (int len = 1; len <= n; len++) {
            
            // 길이가 len인 모든 구간 [l, r]에 대해 dp를 계산
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                long best = Long.MAX_VALUE;
                int bestK = -1;

                // k를 현재 구간 [l, r]에서 첫 삽질 위치로 가정
                // 이번에 d[k] 비용을 쓰고, 결과에 따라 왼쪽 또는 오른쪽 부분 구간으로 진행
                for (int k = l; k <= r; k++) {
                    long left = (k > l) ? dp[l][k - 1] : 0;
                    long right = (k < r) ? dp[k + 1][r] : 0;
                    long cost = d[k] + Math.max(left, right); // 최악의 경우에도 반드시 찾아야 하므로 더 큰 쪽 비용을 반영
                    
                    // 가능한 첫 삽질 위치 후보 중 최소 보장 비용이 가장 작은 위치를 선택
                    if (cost < best) {
                        best = cost;
                        bestK = k;
                    }
                }

                dp[l][r] = best;
                choice[l][r] = bestK;
            }
        }

        int l = 1;
        int r = n;

        while (l <= r) {
            int k = choice[l][r];

            // choice[l][r]에 저장된 최적 위치를 실제로 excavate 하며 구간을 줄여나감
            int res = excavate.apply(k);

            if (res == 0) {
                return k;
            } else if (res < 0) {
                r = k - 1;
            } else {
                l = k + 1;
            }
        }

        return 0;
    }
}