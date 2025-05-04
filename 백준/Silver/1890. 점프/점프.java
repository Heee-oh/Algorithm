import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static long[][] dp;
    static int[] dy = {1, 0};
    static int[] dx = {0, 1};

    static int r;
    static int c;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        r = c = n;
        map = new int[r][c];
        dp = new long[r][c];


        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));
    }


    private static long dfs(int y, int x) {
        // 도착점에 왔다면 1가지 루트가 생겼으므로 1 반환
        if (r-1 == y && c - 1 == x) return 1;
        // 이미 가는 방법이 있다면 그 경우의 수를 반환
        if (dp[y][x] != -1) return dp[y][x];

        // dp[y][x]에서 r-1,c-1로 가는 경우의 수를 구하기 위해 0으로 초기화
        dp[y][x] = 0;
        int jumpValue = map[y][x]; // 점프해야하는 칸의 값

        for (int i = 0; i < 2; i++) {
            int nextR = y + (jumpValue * dy[i]);
            int nextC = x + (jumpValue * dx[i]);

            if (nextR < 0 || nextR >= r || nextC < 0 || nextC >= c) {
                continue;
            }

            // 아래, 오른쪽 방향으로 가능한 곳을 탐색해서 dp[y][x]에서 r-1,c-1로 도달하는 경우의 수를 구한다.
            dp[y][x] += dfs(nextR, nextC);
        }

        // y,x에서 r-1, c-1로 가는 경우의 수를 반환
        return dp[y][x];
    }


}