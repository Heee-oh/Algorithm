import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int N = fr.nextInt();
        int T = fr.nextInt();
        T = (T & 1) != 0 ? T - 1 : T; // 홀수면 -1


        long[] happiness = new long[N];
        long[] dist = new long[N];
        long[] pSum = new long[N];

        for (int i = 0; i < N; i++) {
            happiness[i] = fr.nextInt();
            if (i > 0) {
                dist[i - 1] += happiness[i - 1] + happiness[i];
                pSum[i] = pSum[i - 1] + happiness[i];
            } else {
                pSum[i] = happiness[i];
            }
        }

        dist[N - 1] = happiness[N - 1];
        long max = 0;

        for (int i = 0; i < N; i++) {
            long sum = 0;
            int t = T;

            // 되돌아 올 수 있는 i 분도 없다면 더이상 진행 불가
            if (t - i < i) break;

            t -= i;
            sum += pSum[i]; // i번까지 이동

            // i 번에서 왕복
            int cnt = (t - i) / 2;
            sum += cnt * dist[i];

            if (i > 0) {
                sum += pSum[i - 1];
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);

    }

    static class FastReader {
        private final StringBuilder sb = new StringBuilder();
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


        String next() throws IOException {
            sb.delete(0, sb.length());
            int c = read();
            while (c != '\n') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            int c = read();
            while (c <= 32) c = read(); // 특수문자 공백등 무시
            int sign = 1;
            if (c == '-') {sign = -1; c = read();}
            int val = 0;

            while (c > 32) { // 숫자면 계속 읽음
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }


}
