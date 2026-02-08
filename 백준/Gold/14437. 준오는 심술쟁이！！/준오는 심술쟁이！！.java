import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static final long MOD = (long) (1e9 + 7);
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        FastReader fr = new FastReader();
        int s = fr.nextInt();
        int N = fr.next().length();

        int[] dp = new int[s + 1];
        dp[0] = 1; // 아무것도 변경하지 않는 경우

        // 각각의 문자에 대하여
        for (int i = 0; i < N; i++) {
            int[] nDp = new int[s + 1];

            long window = 0;
            for (int k = 0; k <= s; k++) {
                window += dp[k];
                if (k > 25) window -= dp[k - 26];

                window = (window + MOD) % MOD;

                nDp[k] = (int)window;

            }
            dp = nDp;
        }

        System.out.println(dp[s]);





    }



    static class FastReader {
        private final StringBuilder sb = new StringBuilder();
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

        String next() throws IOException {
            sb.delete(0, sb.length());

            int c = read();
            while (c != '\n') {
                sb.append((char)c);
                c = read();
            }
            return sb.toString();
        }
        int nextInt() throws IOException {
            int c = read();
            while (c <= 32) c = read();
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            int val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
        long nextLong() throws IOException {
            int c = read();
            while (c <= 32) c = read();
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            long val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}
