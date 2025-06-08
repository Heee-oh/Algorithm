import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());


        int[] costs = new int[N + 1];
        int[] customers = new int[N + 1];

        // 도시 , 1~C
        int[] dp = new int[C + 100];

        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            // customer ~ C+100 까지 (인덱스이므로 사실상 C+99)
            for (int j = customer; j < C + 100; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - customer]);
            }
        }


        int answer = Integer.MAX_VALUE;
        for (int i = C; i < C + 100; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);

    }
}
