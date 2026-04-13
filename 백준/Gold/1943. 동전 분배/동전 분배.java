import java.io.*;
import java.util.Arrays;


public class Main {


    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int N =  fr.nextInt();
            int[] coins = new int[N];
            int[] cnt =  new int[N];

            // 입력
            for (int j = 0; j < N; j++) {
                coins[j] = fr.nextInt();
                cnt[j] = fr.nextInt();
            }

            // 1. 총합 계산 및 홀수 체크
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += coins[j] *  cnt[j];
            }

            if((sum & 1) != 0) {
                sb.append("0\n");
                continue;
            }

            int target = sum / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;

            // 2. DP 진행 (배낭 문제)
            for (int j = 0; j < N; j++) {
                int coin = coins[j];
                int count = cnt[j];

                // j를 target부터 거꾸로 확인하여 중복 사용 방지
                for (int c = target; c >= coin; c--) {
                    if (dp[c - coin]) { // 이전 동전들로 (c - coin)원을 만들 수 있었다면
                        // 현재 동전을 1개부터 count개까지 사용해서 만들 수 있는 금액들 체크
                        for (int k = 0; k < count && c + coin * k <= target; k++) {
                            dp[c + coin * k] = true;
                        }
                    }
                }
            }

            sb.append(dp[target] ? 1 : 0).append("\n");



        }

        System.out.println(sb.toString().trim());



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