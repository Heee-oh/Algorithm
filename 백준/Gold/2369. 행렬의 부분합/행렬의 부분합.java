import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();
        int M = fr.nextInt();
        int K = fr.nextInt();

        long cnt = 0;
        int[][] pSum = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int e = fr.nextInt();
                pSum[i][j] = pSum[i-1][j] + pSum[i][j-1] - pSum[i-1][j-1] + e;
                if (e % K == 0) {
                    cnt++;
                }
            }
        }

        for (int dr = 0; dr <= N; dr++) {
            for (int dc = dr == 0 ? 1 : 0; dc <= M; dc++) {

                for (int r = 1; r + dr <= N; r++) {
                    for (int c = 1; c + dc <= M; c++) {
                        int value = pSum[r + dr][c + dc] - (pSum[r + dr][c - 1] + pSum[r - 1][c + dc]) + pSum[r-1][c-1];
                        if (value % K == 0) {
                            cnt++;
                        }

                    }
                }
            }
        }


        System.out.println(cnt);




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
            int c = read();
            while (c <= 32) c = read();
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            int val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}
