import java.io.*;
import java.util.*;

public class Main {

    // 1차원 배열로 가능한가 ?

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][n];
            int[][] dp = new int[2][n];
            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];

            for (int j = 1; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[k][j] = Math.max(dp[k][j-1], dp[(k + 1) % 2][j-1] + sticker[k][j]);
                }
            }

            bw.write(Math.max(dp[0][n-1], dp[1][n-1]) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
