import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] box = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    q.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }

        int answer = bfs(box);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) {
                    System.out.println("-1");
                    return;
                }
            }
        }


        System.out.println(answer);

    }

    private static int bfs(int[][] box) {

        int max = 0;

        while (!q.isEmpty()) {
            int[] curPos = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curPos[0] + dy[i];
                int nextC = curPos[1] + dx[i];

                if (nextR < 0 || nextR >= box.length
                        ||nextC < 0 || nextC >= box[0].length
                        ||visited[nextR][nextC] || box[nextR][nextC] == -1) continue;

                box[nextR][nextC] = 1;
                visited[nextR][nextC] = true;
                q.add(new int[]{nextR, nextC, curPos[2] + 1});
                max = Math.max(max, curPos[2] + 1);
            }
        }

        return max;
    }

}
