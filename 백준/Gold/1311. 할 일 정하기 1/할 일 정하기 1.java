import java.io.*;
import java.util.Arrays;

public class Main {


    static int[] dp;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N =  fr.nextInt();
        int max = (1 << N) - 1;
        dp = new int[max + 1];

        int[][] arr = new int[N][N];
        Arrays.fill(dp, (int) (1e7 + 1));


        // 비용 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = fr.nextInt();
            }
        }


        dp[0] = 0;

        for (int bit = 1; bit <= max; bit++) {
            // 일
            for (int job = 0; job < N; job++) {

                if ((bit & (1 << job)) != 0) {
                    int prevBit = bit ^ (1 << job);

                    // 선택한 일 개수 = 이미 배정한 사람 수
                    int p = Integer.bitCount(prevBit);
                    dp[bit] = Math.min(dp[bit], dp[prevBit] + arr[p][job]);


                }
            }
        }

        System.out.println(dp[max]);
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