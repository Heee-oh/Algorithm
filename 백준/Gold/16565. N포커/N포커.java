
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static final int MOD = 10_007;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();

        if (N < 4) {
            System.out.println(0);
            return;
        }

        int[][] dp = new int[53][53];

        for (int i = 0; i <= 52; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= 52; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        long ans = 0;
        for (int k = 1; k <= N / 4; k++) {
            long rest = dp[52 - 4 * k][N - 4 * k];
            long pickForCard = dp[13][k];

            long cnt = (pickForCard * rest) % MOD;
            if ((k & 1) == 1) {
                ans = (ans + cnt) % MOD;
            } else {
                ans = (ans - cnt + MOD) % MOD;
            }
        }

        System.out.println(ans);


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