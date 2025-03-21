import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] jewel = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewel[i][0] = w;
            jewel[i][1] = v;
        }

        // 무게 별 , 보석
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (jewel[i][0] > j) { // i번째 보석의 무게가 현재 배낭 무게를 넘어선다면
                    dp[i][j] = dp[i - 1][j]; // 이전 i번째 보석의 값을 저장

                } else {
                    // 현재 최댓값 = 이전 i번째 값 , 이전 i번째의 j-현재 주얼 무게 의 최댓값 + 현재 보석의 가치
                    // 이전 i번째를 하는 이유 : 위에서부터 아래로 보석을 담았을 때를 고려해서 순회를 돌기에,
                    // i번 이전에 최댓값을 담았을 수도 있기 때문이다.  
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - jewel[i][0]] + jewel[i][1]);
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
