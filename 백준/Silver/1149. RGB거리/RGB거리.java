import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][3];
        int[][] dp = new int[n][3];

        // 그래프 초기화
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dp[0][0] = graph[0][0];
        dp[0][1] = graph[0][1];
        dp[0][2] = graph[0][2];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if (j != k){
                        dp[i][j] = Math.min(dp[i - 1][k] + graph[i][j], dp[i][j]);
                    }
                }
            }
        }

        int min = Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
        bw.write(min + "");
        bw.flush();
        bw.close();
    }
}