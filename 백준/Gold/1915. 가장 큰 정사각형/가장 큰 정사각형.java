import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        int answer = 0;
        int[][] dp = new int[N + 1][M + 1];
        int[][] arr = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            char[] line = br.readLine().toCharArray();

            for (int j = 1; j <= M; j++) {
                arr[i][j] = line[j - 1] - '0';
            }
        }


        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (arr[i][j] == 0) continue;

                if (arr[i - 1][j - 1] == arr[i - 1][j] && arr[i - 1][j] == arr[i][j - 1]) {
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1] , dp[i-1][j])) + 1;
                } else {
                    dp[i][j] = 1;
                }

                answer = Math.max(dp[i][j], answer);
            }
        }


        System.out.println(answer * answer);

    }

}
