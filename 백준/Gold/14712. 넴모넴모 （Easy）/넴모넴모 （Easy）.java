import java.io.*;
import java.util.*;

public class Main {

    static int N, M, cnt = 0;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        dfs(0);

        System.out.println(cnt);
    }

    private static void dfs(int idx) {
        // 끝에 도달했다면 종료
        if (idx == N * M) {
            cnt++;
            return;
        }

        int r = idx / M;
        int c = idx % M;


        // 현재 칸에 네모를 안두는 경우
        dfs(idx + 1);

        // 2x2 넴모가 생기는지 체크
        if (r > 0 && c > 0) {
            if (visited[r - 1][c] && visited[r - 1][c - 1] && visited[r][c - 1]) {
                return;
            }
        }

        // 현재 칸에 네모를 두는 경우
        visited[r][c] = true;
        dfs(idx + 1);
        visited[r][c] = false;

    }

}
