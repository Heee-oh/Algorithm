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
        int[][] dp = new int[N + 1][C + 101];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            costs[i] = Integer.parseInt(st.nextToken());
            customers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp[0], Integer.MAX_VALUE);

        for (int c = 1; c <= C + 100; c++) {
            for (int city = 1; city <= N; city++) {

                // j홍보만 사용하여 c를 만족하는 경우
                int cnt = (int) Math.ceil((double) c / customers[city]);
                dp[city][c] = cnt * costs[city];

                // j홍보 1개를 사용하면서 다른 최소값 홍보를 사용하는 경우
                if (c >= customers[city]) {
                    dp[city][c] = Math.min(dp[city][c], costs[city] + dp[city][c - customers[city]]);

                }

                dp[city][c] = Math.min(dp[city][c], dp[city-1][c]);
            }

        }

        int answer = Integer.MAX_VALUE;
        for (int i = C; i <= C + 100; i++) {
            answer = Math.min(answer, dp[N][i]);
        }
        System.out.println(answer);

    }
}
