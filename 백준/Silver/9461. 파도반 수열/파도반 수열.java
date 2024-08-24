import java.io.*;
import java.util.*;

public class Main {

    static boolean[] visited;
    static boolean[][] graph;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[101];

        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;

        for (int i = 6; i <= 100; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }


        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(dp[n] + "\n");
        }


        bw.flush();
        bw.close();
    }

}