import java.io.*;
import java.util.*;

public class Main {
    static char[][] graph;
    static boolean[] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int answer = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        visited = new boolean[26]; // 26문자



        for (int i = 0; i < R; i++) {
            String alpha = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = alpha.charAt(j);
            }
        }


        visited[graph[0][0] - 'A'] = true;

        recursion(0, 0, 1);
        System.out.println(answer);
    }

    private static void recursion(int y, int x, int cnt) {
        answer = (answer < cnt) ? cnt : answer;

        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];

            if (nextX >= 0 && nextX < graph[0].length
                && nextY >= 0 && nextY < graph.length
                && !visited[graph[nextY][nextX] - 'A']) {

                int alphaIndex = graph[nextY][nextX] - 'A';
                visited[alphaIndex] = true;
                recursion(nextY, nextX, cnt + 1);
                visited[alphaIndex] = false;
            }
        }


    }
}