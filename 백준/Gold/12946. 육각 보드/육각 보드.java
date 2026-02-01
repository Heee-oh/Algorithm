import java.io.*;
import java.util.*;

public class Main {


    static int[] dr = {-1, -1, 0, 1, 1, 0};
    static int[] dc = {0, 1, 1, 0, -1, -1};
    static char[][] map;
    static int xCnt = 0;
    static int N;
    static boolean hasEdge = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        int[][] colorMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            Arrays.fill(colorMap[i], -1);
        }

        if (N == 1) {
            if (map[0][0] == 'X') {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
            return;
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'X' && colorMap[i][j] == -1) {
                    colorMap[i][j] = 0;
                    dfs(colorMap, i, j);
                    xCnt++;
                }
            }
        }

        if (xCnt == 0) {
            System.out.println(0);
        } else {
            System.out.println(hasEdge ? 2 : 1);
        }

    }

    private static void dfs(int[][] colorMap, int r, int c) {
        // 가능 한 모든 경우의 종류를 넣어보기

        for (int j = 0; j < 6; j++) {
            int nr = r + dr[j];
            int nc = c + dc[j];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N
                    || map[nr][nc] == '-') {
                continue;
            }


            if (colorMap[nr][nc] == -1) {
                hasEdge = true;
                colorMap[nr][nc] = 1 - colorMap[r][c];
                dfs(colorMap, nr, nc);
            } else {

                if (colorMap[nr][nc] == colorMap[r][c]) {
                    System.out.println(3);
                    System.exit(0);

                }
            }


        }

    }
}
