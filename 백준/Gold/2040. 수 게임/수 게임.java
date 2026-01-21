import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] dp;
    public static void main(String[] args) throws Exception {
//        FastReader fr = new FastReader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] A = new int[N + 1];
            dp = new int[N];
            Arrays.fill(dp, Integer.MAX_VALUE);

            // 거꾸로 누적합 1 2 3 이면 A[2] = A[3] + 3 A[1] = A[2] + 2
            // 6 3 1
            for (int j = 0; j < N; j++) {
                A[N - j - 1] = A[N - j] + Integer.parseInt(st.nextToken());
            }

            select(A, 0);

            if (dp[0] == 0) {
                sb.append("D\n");
            } else if (dp[0] > 0) { // A가 선 내가 먹은 점수 - 상대가 먹은 최선 점수 이므로 0초과시 A가 크다.
                sb.append("B\n");
            } else {
                sb.append("A\n");
            }
        }

        System.out.print(sb.toString().trim());
    }

    private static int select(int[] A, int k) {
        if (k == N) {
            return 0;
        }
        if (dp[k] != Integer.MAX_VALUE) {
            return dp[k];
        }

        // k ~ k+ i + 1 까지 모든 경우의 수 고려
        for (int i = 0; i < N - k; i++) {
                                    // 내가먹은 점수 - 상대방이 먹은 최선의 최소 점수
            dp[k] = Math.min(dp[k], A[k] - A[k + i + 1]  - select(A, k + i + 1));
        }

        return dp[k];
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
