import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int[] dp = new int[100];
        Arrays.fill(dp, (int) (1e6));
        dp[0] = 0;
        
        // 코인이 아래 값들이 100제곱 단위로 반복됨
        // 1 10 25, 100, 1_000, 2_500, 10_000 100_000 250_000, ...
        int[] coin = {1, 10, 25};

        // 배낭 문제 
        for (int c : coin) {
            for (int i = c; i < 100; i++) {
                dp[i] = Math.min(dp[i], dp[i - c] + 1);
            }
        }

        int T = fr.nextInt();
        while (T-- > 0) {
            long n = fr.nextLong();
            long ans = 0;

            while (n > 0) {
                ans += dp[(int)(n % 100)]; // 100미만의 값을 만드는 최소 개수를 바로 구함
                n /= 100;
            }
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString().trim());

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

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}