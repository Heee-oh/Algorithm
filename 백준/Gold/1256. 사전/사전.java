
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static long[][] dp;
    static final long LIMIT = 1_000_000_001L;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();
        int M = fr.nextInt();
        int K = fr.nextInt();

        dp = new long[N + 1][M + 1];


        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }


        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i <= M; i++) {
            dp[0][i] = 1;
        }

        long total = dfs(N, M);

        if (total < K) {
            System.out.println(-1);
            return;
        }


        StringBuilder sb = new StringBuilder();
        int a = N, z = M;
        long k = K;


        while (a > 0 && z > 0) {
            long left = dp[a-1][z]; // 맨 앞에 a를 붙였을 때 가능한 개수

            if (k <= left) {
                sb.append('a');
                a--;
            } else {
                sb.append('z');
                k -= left;
                z--;
            }
        }

        while (a-- > 0) sb.append('a');
        while (z-- > 0) sb.append('z');


        System.out.println(sb.toString());
    }

    private static long dfs(int a, int z) {
        if (a < 0 || z < 0) {
            return 0L;
        }

        if (dp[a][z] != -1) {
            return dp[a][z];
        }

        dp[a][z] = Math.min(LIMIT, dfs(a - 1, z) + dfs(a, z - 1));
        return dp[a][z];
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