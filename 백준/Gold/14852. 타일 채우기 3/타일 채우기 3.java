import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();
        long[] dp = new long[N + 1];
        long[] s = new long[N + 1];
        dp[0] = 1;
        s[0] = 1;

        if (N >= 1) {
            dp[1] = 2;
            s[1] = (s[0] + dp[1]) % MOD;
        }
        if (N >= 2) {
            dp[2] = 7;
            s[2] = (s[1] + dp[2]) % MOD;
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = (
                     (dp[i - 2] * 3) % MOD 
                     + (dp[i - 1] * 2) % MOD 
                     + (s[i - 3] * 2) % MOD
            ) % MOD;

            s[i] = (s[i - 1] + dp[i]) % MOD;
        }



        System.out.println(dp[N]);
    }



    // 메모리 효율을 극대화한 입력 클래스
    private static class FastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024 * 16];
        private int ptr = 0;
        private int len = 0;

        private int read() throws IOException {
            if (ptr < len) return buffer[ptr++];
            len = in.read(buffer);
            ptr = 0;
            if (len <= 0) return -1;
            return buffer[ptr++];
        }

        public String next() throws IOException {
            int b = read();
            while (b != -1 && b <= 32) b = read();
            if (b == -1) return null;
            StringBuilder sb = new StringBuilder();
            while (b > 32) {
                sb.append((char) b);
                b = read();
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            int n = 0;
            int b = read();
            while (b != -1 && b <= 32) b = read();

            // -도 처리
            int sign = 1;
            if (b =='-') {
                sign = -1;
                b = read();
            }
            while (b > 32) {
                n = n * 10 + (b - '0');
                b = read();
            }
            return n * sign;
        }
    }

}
