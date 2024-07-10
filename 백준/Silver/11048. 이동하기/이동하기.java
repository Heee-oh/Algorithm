import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] maze = new int[n+1][m+1];

//        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = maze[i][j] + Math.max(Math.max(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);

            }
        }

        System.out.println(dp[n][m]);

        // 1,1 부터 시작해서
        // 현재값(maze) +
        // dp 값 x - 1, y - 1, x-1 y-1 중 가장 큰수를 dp[현재]에 저장




    }


}