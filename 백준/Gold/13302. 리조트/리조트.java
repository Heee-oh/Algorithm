import java.io.*;
import java.util.*;

public class Main {

    static boolean[] block;
    static int[][] dp;
    static int N;
    static final int MAX = (int) (1e7 + 1);
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        int M = fr.nextInt();
        block = new boolean[N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            int n = fr.nextInt();
            block[n] =  true;
        }


        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], MAX);
        }

        int dfs = dfs(1, 0);
        System.out.println(dfs);


    }

    private static int dfs(int n, int coupon) {
        if (n > N) return 0;


        if (dp[n][coupon] != MAX) {
            return dp[n][coupon];
        }

        if (!block[n]) {
            if (coupon >= 3) { // 쿠폰 사용 or 사용 X
                dp[n][coupon] = Math.min(dfs(n + 1, coupon - 3), dfs(n + 1, coupon) + 10000);
            } else {
                dp[n][coupon] = Math.min(dp[n][coupon], dfs(n + 1,  coupon) + 10000);
            }
        } else {
            dp[n][coupon] = Math.min(dp[n][coupon], dfs(n + 1,  coupon));
        }


//        // 하루 이용권
//        if (coupon >= 3) { // 쿠폰 사용 or 사용 X
//            dp[n][coupon] = Math.min(dfs(n + 1,  coupon - 3), dfs(n + 1,  coupon));
//        } else { // 쿠폰 3미만
//            dp[n][coupon]= Math.min(dp[n][coupon], dfs(n + 1,  coupon));
//        }

        // 3일 이용권
        dp[n][coupon] = Math.min(dp[n][coupon], dfs(n + 3,coupon + 1) + 25000);

        // 5일 이용권
        dp[n][coupon] = Math.min(dp[n][coupon], dfs(n + 5,coupon + 2) + 37000);

        return dp[n][coupon];

    }



    static class FastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}