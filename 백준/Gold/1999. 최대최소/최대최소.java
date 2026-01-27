import java.io.*;
import java.util.*;

public class Main {

    // 행에 대한 구간 최대 최소값을 dp로 메모라이징
    static class Node {
        int max, min;

        public Node(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        FastReader fr = new FastReader();

        int N = fr.nextInt();
        int B = fr.nextInt() - 1;
        int K = fr.nextInt();
        int[][] arr = new int[N + 1][N + 1];
        Node[][][] dp = new Node[N + 1][N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = fr.nextInt();
                dp[i][j][j] = new Node(arr[i][j], arr[i][j]);
            }
        }


        for (int i = 1; i < N; i++) {
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N - i; c++) {
                    Node a = dp[r][c][c];
                    Node b = dp[r][c + 1][c + i];

                    int max = Math.max(a.max, b.max);
                    int min = Math.min(a.min, b.min);
                    dp[r][c][c + i] = new Node(max, min);
                }
            }
        }

        while (K-- > 0) {
            int r1 = fr.nextInt();
            int r2 = r1 + B;

            int c1 = fr.nextInt();
            int c2 = c1 + B;

            int max = -1;
            int min = 1000;

            for (int i = r1; i <= r2; i++) {
                max = Math.max(max, dp[i][c1][c2].max);
                min = Math.min(min, dp[i][c1][c2].min);

            }

            sb.append(max - min).append("\n");
        }

        System.out.print(sb.toString().trim());
    }


    // 메모리 효율을 극대화한 입력 클래스
    private static class FastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024 * 16];
        private int ptr = 0;
        private int len = 0;

        private int read() throws IOException {
            if (ptr < len) return buffer[ptr++];
            len = in.read(buffer);
            ptr = 0;
            if (len <= 0) return -1;
            return buffer[ptr++];
        }

        public String next() throws IOException {
            int b = read();
            while (b != -1 && b <= 32) b = read();
            if (b == -1) return null;
            StringBuilder sb = new StringBuilder();
            while (b > 32) {
                sb.append((char) b);
                b = read();
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            int n = 0;
            int b = read();
            while (b != -1 && b <= 32) b = read();

            // -도 처리
            int sign = 1;
            if (b =='-') {
                sign = -1;
                b = read();
            }
            while (b > 32) {
                n = n * 10 + (b - '0');
                b = read();
            }
            return n * sign;
        }
    }


}
