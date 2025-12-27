import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dp = new int[M+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 무게
            int C = Integer.parseInt(st.nextToken()); // 만족도
            int K = Integer.parseInt(st.nextToken()); // 개수


            for (int k = 1; K > 0; k<<=1) {
                int num = Math.min(k, K);
                int w = V * num;
                int c = C * num;

                for (int j = M; j >= w; j--) {
                    dp[j] = Math.max(dp[j], dp[j-w] + c);
                }
                K -= num;
            }
        }
        System.out.println(dp[M]);

    }

}