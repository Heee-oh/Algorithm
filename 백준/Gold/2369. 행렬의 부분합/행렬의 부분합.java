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
        int[][] arr = new int[N][M ];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int e = fr.nextInt();
                arr[i][j] = e;

            }
        }

        int[] freq = new int[K]; // K가 최대 100만이므로 매번 초기화하거나 생성할 수 없음
        int[] col = new int[M + 1];
        int[] used = new int[M + 1]; // freq에 사용한 나머지 값을을 기록
        int usedIdx;

        for (int top = 0; top < N; top++) {
            Arrays.fill(col, 0);

            for (int bottom = top; bottom < N; bottom++) {

                // 각 열대대한 누적합
                for (int c = 0; c < M; c++) {
                    col[c] += arr[bottom][c];
                }


                usedIdx = 0;
                int prefix = 0;
                freq[0] = 1;
                // 해당 열 구간 L..R 에 대하여 누적합은
                // pSum[R] - pSum[L-1] % K == 0
                // -> pSum[R] % K == pSum[L-1] % K
                // 따라서 매 구간의 나머지의 개수를 기록한다.

                for (int c = 0; c < M; c++) {
                    prefix = (prefix + col[c]) % K;
                    cnt += freq[prefix]; // 현재 prefix를 R로 보고 pSum[L-1] % K 개 만큼 더함
                    if (freq[prefix] == 0) {
                        used[usedIdx++] = prefix;
                    }
                    freq[prefix]++; // 현재 열의 나머지도 L이 되므로 +1
                }

                // 매 바텀이 확장될때마다 초기화
                for (int i = 0; i < usedIdx; i++) {
                    freq[used[i]] = 0;
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
