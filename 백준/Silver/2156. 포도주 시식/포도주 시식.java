import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] =  fr.nextInt();
        }

        dp[1] = arr[1];

        for (int i = 2; i <= N; i++) {
            if (i == 2) {
                dp[i] = arr[2] + arr[1];
                continue;
            }
            dp[i] = Math.max(dp[i - 1], // 현재 i 번째 포도주를 먹지 않는 경우  
                    Math.max(arr[i] + dp[i - 2],  // i번째 + 점프
                            arr[i] + arr[i - 1] + dp[i - 3] // i-1, i + 점프
                    )
            );
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