import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    static boolean[][][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        graph = new int[h][w];
        visited = new boolean[h][w][31];

        // 그래프 값 초기화 (평지 생성중...)
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        bw.write( bfs(h - 1, w - 1, k) + "\n");

        bw.flush();
        bw.close();
    }

    private static int bfs(int h, int w, int cnt) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, cnt, 0});
        visited[0][0][cnt] = true;

        int min = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] loc = q.poll();

            if (loc[0] == h && loc[1] == w) {
                return loc[3];
            }

            for (int i = 0; i < 4; i++) {
                int nextY = loc[0] + dy[i];
                int nextX = loc[1] + dx[i];
                int k = loc[2];

                // 말 처럼 이동
                if (k > 0) {
                    int idx = i < 2 ? 2 : 0;
                    
                    for (int j = idx; j < idx + 2; j++) {
                        int horseY = nextY + dy[i] + dy[j];
                        int horseX = nextX + dx[i] + dx[j];

                        if (!checkBound(horseY, horseX, k - 1)) {
                            q.add(new int[]{horseY, horseX, k - 1, loc[3] + 1});
                            visited[horseY][horseX][k - 1] = true;
                        }
                    }
                }

                if (!checkBound(nextY, nextX, k)) {
                    q.add(new int[]{nextY, nextX, k, loc[3] + 1});
                    visited[nextY][nextX][k] = true;
                }
            }
        }
        return -1;
    }

    private static boolean checkBound(int nextY, int nextX, int k) {
        return nextY < 0 || nextY >= graph.length ||
                nextX < 0 || nextX >= graph[0].length || visited[nextY][nextX][k] || graph[nextY][nextX] == 1;
    }

}