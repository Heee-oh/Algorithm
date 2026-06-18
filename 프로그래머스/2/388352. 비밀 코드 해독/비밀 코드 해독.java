import java.util.*;

class Solution {
    
    int[] number;
    boolean[][] check;
    int answer = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        check = new boolean[q.length][n + 1];
        
        for (int i = 0; i < q.length; i++) {
            for (int num : q[i]) {
                check[i][num] = true;
            }
        }
        
        
        dfs(1, 0, n, new int[5], ans);
        
        return answer;
    }
    
    
    private void dfs(int idx, int size, int n, int[] arr, int[] ans) {
        if (size == 5) {
            int checkCnt = 0;
            for (int i = 0; i < check.length; i++) {
                int cnt = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (check[i][arr[j]]) {
                        cnt++;
                    }
                }
                
                if (ans[i] == cnt) {
                    
                    checkCnt++;
                }
            }
            if (checkCnt == ans.length) {
                answer++;
            }
            return;
            
        }
        
        if (idx > n) {
            return;
        }
        
        for (int i = idx; i <= n; i++) {
            arr[size] = i;
            dfs(i + 1, size + 1, n, arr, ans);
        }
    }
    
}