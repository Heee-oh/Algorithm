import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int[][] map;
    static int[][] diff;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cnt = 1;

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            min = Integer.MAX_VALUE;
            map = new int[n][n];
            diff = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    diff[i][j] = Integer.MAX_VALUE;
                }
            }

            bfs();

            sb.append("Problem ").append(cnt++).append(": ").append(diff[n-1][n-1]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void bfs() {
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        q.add(new int[]{0, 0, map[0][0]});
        diff[0][0] = map[0][0];

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dy[i];
                int nextC = cur[1] + dx[i];

                if (nextR < 0 || nextR >= map.length || nextC < 0 || nextC >= map.length) continue;

                if (diff[nextR][nextC] > cur[2] + map[nextR][nextC]) {
                    diff[nextR][nextC] = cur[2] + map[nextR][nextC];
                    q.add(new int[]{nextR, nextC, diff[nextR][nextC]});
                }
            }
        }

    }

}
