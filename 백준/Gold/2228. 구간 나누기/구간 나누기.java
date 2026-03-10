
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();
        int M = fr.nextInt();

        int[] sum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + fr.nextInt();
        }

        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Integer.MIN_VALUE / 2;
            }
        }

        dp[1][1] = sum[1];

        for (int n = 2; n <= N; n++) {
            for (int m = 1; m <= M; m++) {

                // n을 현재 구간에서 미포함하는 경우 이전 최댓값을 이어받음
                dp[n][m] = dp[n - 1][m];

                // m == 1일때 첫번째 구간 구하기
                int min = m == 1 ? -1 : 0;

                // n번째 숫자를 포함하는 경우 한칸 띄기
                for (int k = n-2; k >= min; k--) {
                    if (k < 0) {
                        dp[n][m] = Math.max(dp[n][m], sum[n]);
                    } else {
                        //  k + 1을 건너띔
                        // (1..k)최댓값 [k+1] (k+2 ... n)
                        int tmp = sum[n] - sum[k + 1];
                        dp[n][m] = Math.max(dp[n][m], dp[k][m - 1] + tmp);
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
    }
}