import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int virusCount = Integer.MAX_VALUE;
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int wallCount = 0;

        graph = new int[n][m];
        visited = new boolean[n][m];
        // 그래프 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) wallCount++;
                else if (graph[i][j] == 2) list.add(new int[]{i, j});
            }
        }

        dfs(0, 0, 0);

        bw.write( ((n * m) - (wallCount + virusCount + 3)) + "");
        bw.flush();
        bw.close();
    }

    private static void dfs(int r, int c, int depth) {

        if (depth == 3) {
            int newVirus = bfs(copyArray(graph));
            virusCount = Math.min(virusCount, newVirus);
            return;
        }


        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {

                if (!visited[i][j] && graph[i][j] == 0) {
                    visited[i][j] = true;
                    graph[i][j] = 1;
                    dfs(i, j , depth + 1);
                    visited[i][j] = false;
                    graph[i][j] = 0;
                }
            }
        }
//
//        for (int i = 0; i < 4; i++) {
//            int nextY = r + dy[i];
//            int nextX = c + dx[i];
//
//            if (nextY < 0 || nextY >= graph.length ||
//                    nextX < 0 || nextX >= graph[0].length || visited[nextY][nextX]) continue;
//
//            if (graph[nextY][nextX] == 0) {
//                visited[nextY][nextX] = true;
//                graph[nextY][nextX] = 1;
//                dfs(nextY, nextX, depth + 1);
//                visited[nextY][nextX] = false;
//                graph[nextY][nextX] = 0;
//            }
//
//        }

    }

    private static int bfs(int[][] map) {

        Queue<int[]> q = new LinkedList<>();
        int count = 0;

        for (int[] ints : list) {
            q.add(ints);
        }

        while (!q.isEmpty()) {
            int[] loc = q.poll();
            count++;

            for (int i = 0; i < 4; i++) {

                int nextY = loc[0] + dy[i];
                int nextX = loc[1] + dx[i];

                if (nextY < 0 || nextY >= map.length ||
                        nextX < 0 || nextX >= map[0].length || visited[nextY][nextX]) continue;

                if (map[nextY][nextX] == 0) {
                    map[nextY][nextX] = 2;
                    q.add(new int[] {nextY, nextX});
                }
            }
        }

        return count;
    }

    private static int[][] copyArray(int[][] arr) {
        int[][] tmp = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            tmp[i] = arr[i].clone();
        }
        return tmp;
    }


}