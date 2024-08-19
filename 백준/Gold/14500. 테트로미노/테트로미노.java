import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] graph;
    static boolean[][] visited;

    static ArrayList<String> list = new ArrayList<>();

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new int [n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 3; j++) {
                for (int k = j + 1; k < 4; k++) {
                    sb.append(i).append(j).append(k);
                    list.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
        }


        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                visited[i][j] = true;
                recursion(i, j, 1, 0);
                cross(i,j);
                visited[i][j] = false;
            }
        }


        bw.write(max + "\n");
        bw.flush();
        bw.close();

    }

//    static int[] dx = {0, 0, 1, -1};
//    static int[] dy = {1, -1, 0, 0};

    private static void cross(int r, int c) {
        int tmp;

        for (String seq : list) {
            tmp = graph[r][c];
            for (int i = 0; i < seq.length(); i++) {
                int idx = seq.charAt(i) - '0';

                int nextRow = r + dy[idx];
                int nextCol = c + dx[idx];

                // 범위를 벗어나면 넘긴다.
                if (nextRow < 0 || nextRow >= graph.length
                        || nextCol < 0 || nextCol >= graph[0].length) continue;


                tmp += graph[nextRow][nextCol];
            }

            max = Math.max(max, tmp);
        }

    }

    private static void recursion(int r, int c, int depth, int cost) {
        if (depth == 4) {
            max = Math.max(max, cost + graph[r][c]);
            return;
        }


        for (int i = 0; i < 4; i++) {
            int nextRow = r + dy[i];
            int nextCol = c + dx[i];

            // 범위를 벗어나면 넘긴다.
            if (nextRow < 0 || nextRow >= graph.length
                    || nextCol < 0 || nextCol >= graph[0].length) continue;

            if (visited[nextRow][nextCol]) continue;

            visited[nextRow][nextCol] = true;
            recursion(nextRow, nextCol, depth + 1, cost + graph[r][c]);
            visited[nextRow][nextCol] = false;
        }

    }

}
