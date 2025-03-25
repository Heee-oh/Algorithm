import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        visited = new boolean[n][m];
        int[] curPos = new int[2];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
            if (line.indexOf('I') != -1) {
                curPos[0] = i;
                curPos[1] = line.indexOf('I');
            }
        }

        int answer = bfs(map, curPos);

        bw.write(answer == 0 ? "TT" : (answer + ""));
        bw.flush();
        bw.close();
    }

    private static int bfs(char[][] map, int[] cur) {
        Queue<int[]> q = new LinkedList<>();
        visited[cur[0]][cur[1]] = true;
        q.add(cur);

        int sum = 0;

        while (!q.isEmpty()) {
            int[] curPos = q.poll();

            if (map[curPos[0]][curPos[1]] == 'P') {
                sum++;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = curPos[0] + dy[i];
                int nextC = curPos[1] + dx[i];

                if (nextR < 0 || nextR >= map.length
                        || nextC < 0 || nextC >= map[0].length
                        || visited[nextR][nextC] || map[nextR][nextC] == 'X') continue;

                q.add(new int[]{nextR, nextC});
                visited[nextR][nextC] = true;

            }
        }

        return sum;
    }



}
