import java.io.*;
import java.util.*;

public class Main {

    static long[][] dp;
    static int N, K;
    static final long MOD = (long) (1e9 + 3);
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();

        N = fr.nextInt();
        K = fr.nextInt();

        // 원형에서 인접하지 않게 고를 수 있는 최대 개수는 N / 2
        if (K > N / 2) {
            System.out.println(0);
            return;
        }

        // K == 1 은 어떤 색이든 하나 고르면 되므로 N가지
        if (K == 1) {
            System.out.println(N);
            return;
        }

        dp = new long[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 1번 색을 선택한 경우: 3 ~ N-1 에서 K-1개 선택 => 길이 N-3
        long case1 = dfs(N - 3, K - 1);

        // 메모이제이션 초기화
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 1번 색을 선택하지 않은 경우: 2 ~ N 에서 K개 선택 => 길이 N-1
        long case2 = dfs(N - 1, K);


        System.out.println((case1 + case2) % MOD);

    }

    private static long dfs(int n, int k) {
        if (k == 0) return 1;
        if (n <= 0) return 0;
        if (k > (n + 1) / 2) return 0; // 선형에서 불가능한 경우

        if (dp[n][k] != -1) return dp[n][k];

        dp[n][k] = (dfs(n - 1, k)  // 현재 색을 안뽑고 다음으로 넘김
                + dfs(n - 2, k - 1) // 현재 색을 뽑고 다음으로 넘김
        ) % MOD;
        return dp[n][k];
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