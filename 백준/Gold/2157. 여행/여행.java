import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o2[2] - o1[2];
        });

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 1; i <= m; i++) {
            dp[1][i] = 0;
        }

        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new int[]{a, b, c});

        }

        while (!pq.isEmpty()) {

            int[] airPortInfo = pq.poll();
            int a = airPortInfo[0];
            int b = airPortInfo[1];
            int c = airPortInfo[2];

//            // a 도시를 방문하지 않았다면 방문처리, 자기 자신의 도시는 기내식 점수 0
//            if (dp[a][1] == -1) {
//                dp[a][1] = 0;
//            }

            // a 도시를 j번 째 지났다면 j번째의 기내식 최대값 + a->b로 가는 비행기의 기내식 값
            for (int j = 1; j <= m - 1; j++) {
                if (dp[a][j] != -1) {
                    dp[b][j + 1] = Math.max(dp[b][j + 1], dp[a][j] + c);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= m; i++) {
            max = Math.max(max, dp[n][i]);
        }

        System.out.println(max);

    }

}
