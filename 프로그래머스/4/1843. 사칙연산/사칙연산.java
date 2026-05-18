import java.util.Arrays;

class Solution {
    public int solution(String arr[]) {
        int answer = -1;
        int n = arr.length / 2 + 1;
        if (n == 1) n = 2;
        
        int[][] minDp = new int[n][n];
        int[][] maxDp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(minDp[i], (int)(1e7));
            Arrays.fill(maxDp[i], (int)(1e7) * -1);
        }
        
        for (int i = 0; i < arr.length; i+= 2) {
            int num = i >>> 1;
            minDp[num][num] = maxDp[num][num] = Integer.parseInt(arr[i]);
        }
        
        for (int len = 2; len < arr.length; len += 2) {
            
            for (int l = 0; l < arr.length - len; l += 2) {
                int r = l + len;
                int s = l >>> 1;
                int e = r >>> 1;
                
                for (int mid = l + 1; mid < r; mid += 2) {
                    if (arr[mid].equals("-")) {
                        maxDp[s][e] = Math.max(maxDp[s][e], maxDp[s][(mid - 1) >>> 1] - minDp[(mid+1) >>> 1][e]);    
                        minDp[s][e] = Math.min(minDp[s][e], minDp[s][(mid - 1) >>> 1] - maxDp[(mid+1) >>> 1][e]);    
                    } else {
                        maxDp[s][e] = Math.max(maxDp[s][e], maxDp[s][(mid - 1) >>> 1] + maxDp[(mid+1) >>> 1][e]);
                        minDp[s][e] = Math.min(minDp[s][e], minDp[s][(mid - 1) >>> 1] + minDp[(mid+1) >>> 1][e]);
                    }
                }
            }
        }
        
        
        return maxDp[0][n - 1];
    }
}