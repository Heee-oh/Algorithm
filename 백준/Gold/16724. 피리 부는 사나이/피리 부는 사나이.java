import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 1x1이 주어졌을때는 하나이므로 1반환
        if (n == 1 && m == 1) {
            System.out.println(1);
            return;
        }

        map = new char[n][m];
        visited = new boolean[n][m];
        finished = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            char[] direction = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i] = direction;
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (!visited[i][j]){
                    startR = i;
                    startC = j;
                    dfs(i, j);
                }
            }
        }

        System.out.println(safeZoneCnt);



    }
    static int safeZoneCnt = 0;
    static int startR;
    static int startC;
    static boolean[][] finished;

    //dfs로 싸이클을 찾아 하나의 세이프 존을 둔다.
    private static void dfs(int r, int c) {
        if (visited[r][c]) {
            if (!finished[r][c]) safeZoneCnt++;
            return;
        }

        visited[r][c] = true;
        int directionIdx = getDirectionIdx(map[r][c]);

        int nextR = r + dy[directionIdx];
        int nextC = c + dx[directionIdx];

        dfs(nextR, nextC);
        finished[r][c] = true;
    }



    private static int getDirectionIdx(char c) {
        switch (c) {
            case 'U' : return 0;
            case 'D' : return 1;
            case 'R' : return 2;
            case 'L' : return 3;

            default: return -1;
        }
    }

}