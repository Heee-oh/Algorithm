import java.util.*;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        
        // 계산된 행렬
        int[][][] matrix = new int[n][n][2];
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        // 자기 자신은 1로 초기화
        for (int i = 0; i < n; i++) {
            matrix[i][i] = matrix_sizes[i];
            dp[i][i] = 0;
        }
        
        
        
        for (int r = n-1; r >= 0; r--) {
            for (int c = r + 1; c < n; c++) {
                int s = r, e = c;
                
                // 중간 부분을 나눠서 모든 조합을 구함
                for (int mid = r; mid < c; mid++) {
                    // dp[r][c] = dp[s][e-1] + [s~e-1, e ~ c] 구간
                    int newSize = dp[s][mid] + dp[mid + 1][e] + matrix_multiple(matrix[s][mid], matrix[mid + 1][e]);

                    if (dp[r][c] > newSize) {
                        dp[r][c] = newSize;
                        matrix[r][c][0] = matrix[s][mid][0];
                        matrix[r][c][1] = matrix[mid + 1][e][1];
                    }
                }
            }
        }
        
        
        
        return dp[0][n-1];
    }
    
    private int matrix_multiple(int[] A, int[] B) {
        return A[0] * B[0] * B[1];
    }
}