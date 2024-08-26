import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int max = 1;
        dp = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = i - 1; j >= 1; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j], dp[i]);
                }
            }
            dp[i]++;
            max = Math.max(dp[i], max);
        }

        bw.write(max +"");
        bw.flush();
        bw.close();
    }
}