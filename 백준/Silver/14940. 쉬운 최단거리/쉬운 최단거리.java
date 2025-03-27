import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][] answer = new int[n][m];
        visited = new boolean[n][m];

        int[] target = new int[2];



        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                String num = st.nextToken();
                map[i][j] = num.equals("1") ? -1 : 0;

                // 목표지점 저장
                if (num.equals("2")) {
                    target[0] = i;
                    target[1] = j;
                }
            }
        }
        bfs(map, answer, target);


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == -1) {
                    answer[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.stream(answer[i]).forEach(x -> sb.append(x).append(" "));
            sb.append("\n");
        }



        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static void bfs(int[][] map, int[][] answer, int[] target) {
        Queue<int[]> q = new LinkedList<>();
        visited[target[0]][target[1]] = true;
        q.add(new int[]{target[0], target[1], 0}); // r, c, 거리
        map[target[0]][target[1]] = 2;

        while (!q.isEmpty()) {
            int[] curPos = q.poll();
            answer[curPos[0]][curPos[1]] = curPos[2];

            for (int i = 0; i < 4; i++) {
                int nextR = curPos[0] + dy[i];
                int nextC = curPos[1] + dx[i];

                if (nextR < 0 || nextR >= map.length
                        || nextC < 0 || nextC >= map[0].length
                        || visited[nextR][nextC] || map[nextR][nextC] == 0) continue;

                map[nextR][nextC] = 2;
                q.add(new int[]{nextR, nextC, curPos[2] + 1});
                visited[nextR][nextC] = true;

            }
        }

    }
}
