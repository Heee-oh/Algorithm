import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int virusCount = Integer.MAX_VALUE;
    static ArrayList<int[]> list = new ArrayList<>();
    static ArrayList<int[]> zeroList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int wallCount = 0;

        graph = new int[n][m];

        // 그래프 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) wallCount++;
                else if (graph[i][j] == 2) list.add(new int[]{i, j});
                else zeroList.add(new int[]{i, j});
            }
        }

        dfs(0, 0);

        bw.write( ((n * m) - (wallCount + virusCount + 3)) + "");
        bw.flush();
        bw.close();
    }

    private static void dfs(int start, int depth) {

        if (depth == 3) {
            int newVirus = bfs(copyArray(graph));
            virusCount = Math.min(virusCount, newVirus);
            return;
        }

        for (int i = start; i < zeroList.size(); i++) {
            int[] tmp = zeroList.get(i);
            graph[tmp[0]][tmp[1]] = 1;
            dfs(i + 1, depth + 1);
            graph[tmp[0]][tmp[1]] = 0;
        }
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
                        nextX < 0 || nextX >= map[0].length ) continue;

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