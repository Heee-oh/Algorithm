import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 10_007;
    //학생은 하나만 뽑거나 안뽑아야함
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M, H;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int[] nextDp = new int[H + 1];

        for (int i = 0; i < N; i++) {
            nextDp[0] = 1;

            int[] dp = new int[H+1];
            st = new StringTokenizer(br.readLine());

            int cnt = st.countTokens();
            int[] blocks = new int[cnt];

            for (int j = 0; j < cnt; j++) {
                blocks[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(blocks);

            for (int j = cnt - 1; j >= 0; j--) {
                for (int k = H; k >= blocks[j]; k--) {
                    dp[k] = (dp[k] + nextDp[k - blocks[j]]) % MOD;
                }
            }

            for (int j = 0; j <= H; j++) {
                nextDp[j] = (nextDp[j] + dp[j]) % MOD;
            }
        }

        System.out.println(nextDp[H]);
    }
}
