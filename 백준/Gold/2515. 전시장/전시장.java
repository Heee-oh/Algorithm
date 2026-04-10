import java.io.*;
import java.util.Arrays;


public class Main {

    static int N;
    static int S;
    static class Paint {
        int h, c;

        public Paint(int h, int c) {
            this.h = h;
            this.c = c;
        }

    }


    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();

        N = fr.nextInt();
        S = fr.nextInt();
        Paint[] paints = new Paint[N];
        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            paints[i] = new Paint(fr.nextInt(), fr.nextInt());
        }


        Arrays.sort(paints, (a, b) -> a.h - b.h);

        dp[0] = paints[0].c;

        for (int i = 1; i < N; i++) {

            int target = paints[i].h - S;
            int l = 0, r = i - 1;
            int ans = -1;

            while (l <= r) {
                int mid = (l + r) >>> 1;
                if (paints[mid].h <= target) {
                    ans = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            int pick = paints[i].c;
            if (ans != -1) pick += dp[ans];

            dp[i] = Math.max(dp[i - 1], pick);

        }


        System.out.println(dp[N - 1]);

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