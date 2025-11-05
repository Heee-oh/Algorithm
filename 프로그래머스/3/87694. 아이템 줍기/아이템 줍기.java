import java.util.*;

class Solution {
    static int[][] map = new int[102][102];
    static boolean[][] visited = new boolean[102][102];
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 좌표 2배 확장
        for (int[] r : rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2;
            int x2 = r[2] * 2, y2 = r[3] * 2;
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    map[i][j] = 1; // 직사각형 내부 표시
                }
            }
        }

        // 내부 제거 (테두리만 남김)
        for (int[] r : rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2;
            int x2 = r[2] * 2, y2 = r[3] * 2;
            for (int i = x1 + 1; i < x2; i++) {
                for (int j = y1 + 1; j < y2; j++) {
                    map[i][j] = 0; // 내부는 0으로
                }
            }
        }

        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }

    private int bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], d = cur[2];

            if (x == ex && y == ey) return d;

            for (int k = 0; k < 4; k++) {
                int nx = x + dr[k];
                int ny = y + dc[k];

                if (nx < 0 || ny < 0 
                    || nx > 100 || ny > 100 
                    || visited[nx][ny] || map[nx][ny] != 1) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, d + 1});
            }
        }

        return -1; // 못 찾은 경우 (문제에서는 항상 가능)
    }
}
