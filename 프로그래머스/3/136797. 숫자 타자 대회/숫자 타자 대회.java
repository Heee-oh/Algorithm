import java.util.*;

class Solution {
    public int[][] cost = {
        { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
        { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
        { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
        { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
        { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
        { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
        { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
        { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
        { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
        { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };

    public int[][][] dp; //dp[idx][left][right]
    public String arr;
    public int len;

    
    public int solution(String numbers) {
        arr = numbers;
        len = numbers.length();
        
        dp = new int[len + 1][10][10];
        for (int i = 0; i < len + 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }
        return solve(0, 4, 6);
    }
    
        
    // 각 넘버에 대하여 왼쪽, 오른쪽을 이동시켰을때의 최솟값을 DP로 구하기
    private int solve(int idx, int L, int R) {
        // 끝이므로 0 반환
        if (idx == len) {
            return 0;
        }
        if (dp[idx][L][R] != -1) return dp[idx][L][R];
        
        int num = arr.charAt(idx) - '0';
        int result = Integer.MAX_VALUE;
        
        //왼쪽 손가락으로 움직이기 
        // 오른쪽 넘버가 타깃이 아닐경우 왼쪽을 이동시켜 누르는 경우
        if (num != R) result = Math.min(solve(idx+1, num, R) + cost[L][num], result);
        
        //오른 손가락으로 움직이기
        // 왼쪽 넘버가 타깃이 아닐경우 오른쪽을 이동시켜 누르는 경우
        if (num != L) result = Math.min(solve(idx+1, L, num) + cost[R][num], result);
        
        return dp[idx][L][R] = result; // 결과를 dp에 담고 해당 값을 리턴
    }
}
