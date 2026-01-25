import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 10_007;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();

        int[] dp = new int[N + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
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
