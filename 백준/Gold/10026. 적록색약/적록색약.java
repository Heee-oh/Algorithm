import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static char[][] rb;
    static char[][] color;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        rb = new char[n][n];
        color = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                color[i][j] = c;

                if (c == 'R' || c == 'G') {
                    rb[i][j] = 'R';
                } else {
                    rb[i][j] = c;
                }
            }
        }


        int count = 0;
        for (int i = 0; i < rb.length; i++) {
            for (int j = 0; j < rb[0].length; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, color[i][j], true);
                    count++;
                }
            }
        }

        bw.write(count +" ");

        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }

        count = 0;

        for (int i = 0; i < rb.length; i++) {
            for (int j = 0; j < rb[0].length; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, rb[i][j], false);
                    count++;
                }
            }
        }

        bw.write(count +"");
        bw.flush();
        bw.close();
    }

    private static void bfs(int r, int c, char rgb, boolean check) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r,c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int newX = poll[1] + dx[i];
                int newY = poll[0] + dy[i];

                if (newX < 0 || newX >= rb[0].length ||
                        newY < 0 || newY >= rb.length || visited[newY][newX]) continue;

                if (check) {
                    if (rgb == color[newY][newX]) {
                        q.add(new int[]{newY, newX});
                        visited[newY][newX] = true;
                    }
                } else {
                    if (rgb == rb[newY][newX]) {
                        q.add(new int[]{newY, newX});
                        visited[newY][newX] = true;
                    }
                }

            }
        }

    }


}