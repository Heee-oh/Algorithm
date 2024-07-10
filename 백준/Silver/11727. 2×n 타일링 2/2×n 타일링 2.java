import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
//    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
//        int m = Integer.parseInt(st.nextToken());
        long[] dp = new long[n + 1];
        if (n == 1) {
            System.out.println(1);
            return;
        } else if (n == 2) {
            System.out.println(3);
            return;
        }

        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2] * 2) % 10007;

        }




        System.out.println(dp[n]);

        // 1,1 부터 시작해서
        // 현재값(maze) +
        // dp 값 x - 1, y - 1, x-1 y-1 중 가장 큰수를 dp[현재]에 저장




    }


}