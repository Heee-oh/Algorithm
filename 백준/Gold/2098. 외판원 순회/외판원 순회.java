import java.io.*;
import java.util.Arrays;


public class Main {


    static int[][] dp;
    static int[][] w;
    static int N;
    static int MAX;
    static int MAX_VALUE = (int) (1e8 + 1);

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();

        N = fr.nextInt();
        MAX = 1 << N;
        dp = new int[N][MAX];
        w = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                w[i][j] = fr.nextInt();
            }
        }


        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 1));
    }

    private static int dfs(int cur, int bit) {
        if (bit == MAX - 1) {
            if (w[cur][0] == 0) { // 0 -> 0은 외판원 순회 실패로 막음
                return MAX_VALUE;
            }
            return w[cur][0];
        }

        if (dp[cur][bit] != -1) {
            return dp[cur][bit];
        }
        dp[cur][bit] = MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if ((bit & (1 << i)) != 0) continue; // 방문했다면 넘어가기
            if (w[cur][i] == 0) continue; // 자기자신으로 가기 방지

            int nBit = bit | (1 << i);
            dp[cur][bit] = Math.min(dp[cur][bit], dfs(i, nBit) + w[cur][i]);
        }

        return dp[cur][bit];
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