import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();

        int n = fr.nextInt();
        int[] arr = new int[n + 1];
        int[] prefix = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = fr.nextInt();
            prefix[i] = prefix[i - 1] + arr[i];
        }

        int k = fr.nextInt();

        // sum[i] = i번째 객차를 끝으로 하는 길이 k 구간의 합
        int[] sum = new int[n + 1];
        for (int i = k; i <= n; i++) {
            sum[i] = prefix[i] - prefix[i - k];
        }

        // dp[t][i] = 앞의 i개 객차까지 고려했을 때
        // 소형 기관차 t대를 사용해서 얻을 수 있는 최대 승객 수
        int[][] dp = new int[4][n + 1];

        for (int t = 1; t <= 3; t++) {
            for (int i = t * k; i <= n; i++) {
                dp[t][i] = Math.max(
                        dp[t][i - 1],           // 현재 구간을 선택하지 않음
                        // 이전 기관차의 i-k 번째 구간 + 현재 구간
                        dp[t - 1][i - k] + sum[i] // 현재 구간을 마지막 기관차가 담당
                );
            }
        }

        System.out.println(dp[3][n]);
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