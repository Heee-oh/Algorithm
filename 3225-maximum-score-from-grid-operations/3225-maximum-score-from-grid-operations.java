import java.util.*;

class Solution {
    int n;
    long[][] prefix;

    static final long NEG = Long.MIN_VALUE / 4;

    public long maximumScore(int[][] grid) {
        n = grid.length;

        if (n == 1) return 0;

        prefix = new long[n][n + 1];

        for (int c = 0; c < n; c++) {
            for (int r = 0; r < n; r++) {
                prefix[c][r + 1] = prefix[c][r] + grid[r][c];
            }
        }

        int size = n + 1;

        // height = 검게 칠한 칸 개수
        // 0이면 안 칠함, n이면 전부 칠함
        long[][] dp = new long[size][size];

        // 첫 두 열 높이 선택
        for (int h0 = 0; h0 <= n; h0++) {
            for (int h1 = 0; h1 <= n; h1++) {
                dp[h0][h1] = score(0, h0, h1);
            }
        }

        // col 열의 점수를 확정하면서 진행
        for (int col = 1; col <= n - 2; col++) {
            long[][] next = new long[size][size];

            for (long[] row : next) {
                Arrays.fill(row, NEG);
            }

            for (int mid = 0; mid <= n; mid++) {
                long[] prefixMax = new long[size];

                long best = NEG;
                for (int left = 0; left <= n; left++) {
                    best = Math.max(best, dp[left][mid]);
                    prefixMax[left] = best;
                }

                long[] suffixMax = new long[size + 1];
                Arrays.fill(suffixMax, NEG);

                for (int left = n; left >= 0; left--) {
                    long value = dp[left][mid] + score(col, mid, left);
                    suffixMax[left] = Math.max(suffixMax[left + 1], value);
                }

                for (int right = 0; right <= n; right++) {
                    long caseLeftLessOrEqual =
                            prefixMax[right] + score(col, mid, right);

                    long caseLeftGreater = suffixMax[right + 1];

                    next[mid][right] = Math.max(
                            caseLeftLessOrEqual,
                            caseLeftGreater
                    );
                }
            }

            dp = next;
        }

        long answer = 0;

        // 마지막 열 점수 확정
        for (int left = 0; left <= n; left++) {
            for (int last = 0; last <= n; last++) {
                answer = Math.max(answer, dp[left][last] + score(n - 1, last, left));
            }
        }

        return answer;
    }

    private long score(int col, int self, int neighbor) {
        if (neighbor <= self) return 0;

        // self ~ neighbor-1 행이 흰색이면서 옆에 검은색이 있는 구간
        return prefix[col][neighbor] - prefix[col][self];
    }
}