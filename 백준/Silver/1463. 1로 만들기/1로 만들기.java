import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();
        int[] dp = new int[N + 1];

        Arrays.fill(dp, (int) (1e6+1));
        dp[1] = 0;

        for (int x = 2; x <= N; x++) {
            int min = (int) (1e6 + 1);

            if (x % 3 == 0) {
                min = Math.min(min, dp[x / 3]);
            }

            if (x % 2 == 0) {
                min = Math.min(min, dp[x / 2]);
            }

            min = Math.min(min, dp[x - 1]);
            dp[x] = min + 1;
        }

        System.out.println(dp[N]);
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