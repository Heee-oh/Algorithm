import java.io.*;
import java.util.*;

public class Main {
    static int N, L;         // 길이 N, 1 최대 L개
    static long I;           // 1-indexed
    static long[][] dp;      // dp[o][len] = 길이가 len이고 1이 최대 o개인 개수
    static final long INF = (1L<<62); // 안전 캡

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        I = Long.parseLong(st.nextToken());

        // dp 크기: ones 0..L, len 0..N
        dp = new long[L + 1][N + 1];

        // base
        for (int o = 0; o <= L; o++) dp[o][0] = 1;   // 빈 문자열 1개
        for (int len = 0; len <= N; len++) dp[0][len] = 1; // 모두 0 한 가지

        // fill
        for (int o = 1; o <= L; o++) {
            for (int len = 1; len <= N; len++) {
                dp[o][len] = safeAdd(dp[o][len - 1], dp[o - 1][len - 1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        int onesLeft = L;
        long kth = I;

        // MSB부터 N자리 결정
        for (int pos = N; pos >= 1; pos--) {
            long cnt0 = dp[onesLeft][pos - 1]; // 앞자리를 0으로 두는 경우의 개수
            if (kth <= cnt0) {
                sb.append('0');
            } else {
                sb.append('1');
                kth -= cnt0;
                if (onesLeft > 0) onesLeft--; // 1을 하나 사용
            }
        }

        System.out.println(sb.toString());
    }

    private static long safeAdd(long a, long b) {
        long s = a + b;
        return s >= INF ? INF : s;
    }
}