import java.io.*;
import java.util.Arrays;


public class Main {


    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();
        int M = fr.nextInt();
        int MIN = -1000000;

        int[][] dp = new int[N + 1][M + 1];
        int[] pSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            pSum[i] = pSum[i - 1] + fr.nextInt();
        }

        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = MIN;
            }
        }

        dp[1][1] = pSum[1];

        // 현재 숫자
        for (int n = 2; n <= N; n++) {
            // 구간
            for (int m = 1; m <= M; m++) {
                // n번째 수를 사용하지 않는 경우, 꼭 n번을 포함한 값이 최대가 아닐 수 있기때문
                dp[n][m] = dp[n - 1][m];

                int min = m == 1 ? -1 : 0;

                for (int k = n-2; k >= min; k--) {

                    if (k < 0) {
                        dp[n][m] = Math.max(dp[n][m], pSum[n]);
                    } else {
                        dp[n][m] = Math.max(dp[n][m], dp[k][m - 1] + pSum[n] - pSum[k + 1]);
                    }
                }


            }
        }

        System.out.println(dp[N][M]);

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