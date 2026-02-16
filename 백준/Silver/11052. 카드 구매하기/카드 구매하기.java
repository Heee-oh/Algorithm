import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int p = Integer.parseInt(st.nextToken());
            for (int j = i; j <= N; j++) {
                dp[j] = Math.max(dp[j], dp[j - i] + p);
            }
        }
        System.out.println(dp[N]);
    }
}
