import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 출발도시 1도 m개의 개수안에 포함
        dp[1][1] = 0;

        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a][b] = Math.max(arr[a][b], c);
        }

        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (arr[a][b] == 0) continue;

                // a 도시를 j번 째 지났다면 j번째의 기내식 최대값 + a->b로 가는 비행기의 기내식 값
                for (int j = 1; j < m; j++) {
                    if (dp[a][j] != -1) {
                        dp[b][j + 1] = Math.max(dp[b][j + 1], dp[a][j] + arr[a][b]);
                    }
                }
            }
        }


        System.out.println(Arrays.stream(dp[n]).max().orElse(0));

    }
}
