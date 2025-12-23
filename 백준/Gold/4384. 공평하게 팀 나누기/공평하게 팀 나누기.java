import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] K = new int[N];
        
        for (int i = 0; i < N; i++) {
            K[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(K);
        int aTeamSize = N - N/2;
        int sum = Arrays.stream(K).sum();
        boolean[][] dp = new boolean[aTeamSize + 1][sum + 1];
        dp[0][0] = true;
         
        for (int w : K) {
            
            // 한 팀의 팀원수  부터 탐색
            for (int i = aTeamSize - 1; i >= 0; i--) {
                
                for (int j = sum; j >= w; j--) {
                    dp[i + 1][j] |= dp[i][j - w];
                }
                
            }
        }
        int min = Integer.MAX_VALUE;
        int minW = Integer.MAX_VALUE;
        
        for (int i = 0; i <= sum; i++) {
            if (!dp[aTeamSize][i]) continue;
            // sum - i : bTeam
            // i : aTeam
            // 두 팀 몸무게 차이 = |(sum - i) - i| = sum - 2*i
            int diff = Math.abs(sum - (i * 2));
            if (min > diff) {
                min = diff;
                minW = i;
                
            } 
        }
        
        System.out.println(Math.min(minW, sum - minW) +  " " + Math.max(minW, sum - minW));
    }
}