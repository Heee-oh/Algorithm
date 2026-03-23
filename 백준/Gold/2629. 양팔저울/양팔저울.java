import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int N =  fr.nextInt();

        boolean[] dp = new boolean[40_001];

        // 무게 추
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = fr.nextInt();
        }

        dp[0] = true;

        for (int j = 0; j < N; j++) {
            for (int k = 40000; k >= arr[j]; k--) {
                dp[k] |= dp[k - arr[j]];
            }
        }


        int M = fr.nextInt();
        for (int i = 0; i < M; i++) {
            int target = fr.nextInt();
            String ans = "N";

            for (int j = 0; j <= 40000 - target; j++) {
                if (dp[j] && dp[j + target]) {
                    ans = "Y";
                    break;
                }
            }

            sb.append(ans).append(" ");
        }


        System.out.print(sb.toString().trim());
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