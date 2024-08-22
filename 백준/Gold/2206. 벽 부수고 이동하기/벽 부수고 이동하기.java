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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());



        graph = new int[n][m];
        visited = new boolean[n][m][2]; // 벽을 부순 경우 , 안부순 경우

        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        bw.write(bfs(n - 1,m - 1) + "\n");
        bw.flush();
        bw.close();
    }

    private static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1,1}); // r, c , 이동거리, 벽부쉈는지 여부
        visited[0][0][1] = true; // 0 : 벽 부숨, 1 : 안부숨

        while (!q.isEmpty()) {

            int[] poll = q.poll();
            if (r == poll[0] && c == poll[1]) return poll[2];

            for (int i = 0; i < 4; i++) {
                int newY = poll[0] + dy[i];
                int newX = poll[1] + dx[i];
                int count = poll[2];
                int check = poll[3];

                if (validBound(newY, newX, check)) continue;

                // 다음이 벽이고, 아직 벽을 안부쉈다면
                if (check == 1 && graph[newY][newX] == 1) check = 0;
                else if (graph[newY][newX] == 1) continue;
                
                q.add(new int[] {newY, newX, count + 1, check});
                visited[newY][newX][check] = true;
//
//                if (check == 1 && graph[newY][newX] == 1) {
//                    q.add(new int[] {newY, newX, count + 1, 0});
//                    visited[newY][newX][0] = true;
//
//                } else if (graph[newY][newX] == 0){
//                    q.add(new int[] {newY, newX, count + 1, check});
//                    visited[newY][newX][check] = true;
//                }

            }
        }

        return -1;
    }

    private static boolean validBound(int newY, int newX, int check) {
        return newY < 0 || newY >= graph.length
                || newX < 0 || newX >= graph[0].length
                || visited[newY][newX][check];
    }


}