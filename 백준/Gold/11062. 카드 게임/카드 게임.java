
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static Integer[][] dp;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int T = fr.nextInt();

        while (T-- > 0) {
            int N = fr.nextInt();
            dp = new Integer[N][N];

            int total = 0;
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = fr.nextInt();
                total += arr[i];
            }

            // 현재 점수 최대 합 - 상대 점수 최대 합 = diff
            // total = 현재 점수  최대 합+ 상대 점수 최대 합
            // 2(현재 점수) = diff + total

            int diff = dfs(0, N - 1);
            int first = (total + diff) >>> 1;

            sb.append(first).append("\n");

        }

        System.out.println(sb.toString().trim());
    }

    // 현재 플레이어 - 상대 플레이어
    private static int dfs(int l, int r) {
        if (l == r) return arr[l];
        if (dp[l][r] != null) return dp[l][r];

        int left = arr[l] - dfs(l + 1, r);
        int right = arr[r] - dfs(l, r - 1);

        return dp[l][r] = Math.max(left, right);
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