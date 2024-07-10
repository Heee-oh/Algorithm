import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static boolean[] visited;
    public static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());
//        visited = new boolean[n + 1];
        dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        if ( n < 2) {
            System.out.println(dp[n]);
            return;
        }

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }


        System.out.println(dp[n]);


    }


}