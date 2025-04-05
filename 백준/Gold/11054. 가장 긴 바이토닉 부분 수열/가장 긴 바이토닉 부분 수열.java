import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] seq;
    static int[] r_dp;
    static int[] l_dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        seq = new int[n];
        r_dp = new int[n];
        l_dp = new int[n];
        // 수열 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }


        LIS();
        LDS();


        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, r_dp[i] + l_dp[i]);
        }




        bw.write( (max - 1) + " ");
        bw.flush();
        bw.close();
    }

    private static void LIS() {
        for (int i = 0; i < n; i++) {
            r_dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (seq[j] < seq[i] && r_dp[i] < r_dp[j] + 1) {
                     r_dp[i] = r_dp[j] + 1;
                }
            }
        }
    }

    private static void LDS() {
        for (int i = n - 1; i >= 0; i--) {
            l_dp[i] = 1;

            for (int j = n - 1; j > i; j--) {
                if (seq[j] < seq[i] && l_dp[i] < l_dp[j] + 1) {
                    l_dp[i] = l_dp[j] + 1;
                }
            }
        }
    }

}