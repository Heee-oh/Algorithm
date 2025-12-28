import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = N - 1; i >= 0; i--) {
            int C =  Integer.parseInt(st.nextToken());

            for (int j = K; j >= C; j--) {
                if (dp[j - C] != -1) {
                    if (dp[j] != -1) {
                        dp[j] = Math.min(dp[j], dp[j - C] + 1);
                    } else {
                        dp[j] =  dp[j - C] + 1;

                    }

                }
            }
        }
        System.out.println(dp[K]);

    }

}