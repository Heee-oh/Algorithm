import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static int M;
    static List<Node> x;
    static Node s, e;
    static int answer = Integer.MAX_VALUE;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[M][N];
        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
        }

        x = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'S') {
                    s = new Node(i, j);

                } else if (map[i][j] == 'X') {
                    x.add(new Node(i, j));

                } else if (map[i][j] == 'E') {
                    e = new Node(i, j);
                }
            }
        }


        int[][][] dist = new int[1 + x.size()][M][N]; // s, 각 x위치를 시작점으로 했을때 각 최단경로

        for (int i = 0; i < dist.length; i++) {
            if (i == 0) {
                dijkstra(s, i, map, dist);
            } else {
                dijkstra(x.get(i - 1), i, map, dist);
            }
        }

        boolean[] visited = new boolean[dist.length];
        dfs(0, dist, visited, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int idx, int[][][] dist, boolean[] visited, int sum, int depth) {
        if (depth == x.size()) {
            answer = Math.min(answer, sum + dist[idx][e.r][e.c]);
            return;
        }

        for (int i = 1; i <= x.size(); i++) {
            if (visited[i]) continue;
            Node node = x.get(i - 1);
            visited[i] = true;
            dfs(i, dist, visited, sum + dist[idx][node.r][node.c], depth + 1);
            visited[i] = false;
        }


    }

    private static void dijkstra(Node s, int idx, char[][] map, int[][][] dist) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[M][N];
        q.offer(new int[]{s.r, s.c, 0});
        visited[s.r][s.c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            if (map[r][c] == 'E') {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (visited[nr][nc] || map[nr][nc] == '#') continue;

                visited[nr][nc] = true;
                dist[idx][nr][nc] = cur[2] + 1;
                q.offer(new int[]{nr, nc, dist[idx][nr][nc]});

            }
        }
    }


    static class FastReader {
        private final StringBuilder sb = new StringBuilder();
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            sb.delete(0, sb.length());

            int c = read();
            while (c != '\n') {
                sb.append((char)c);
                c = read();
            }
            return sb.toString();
        }
        int nextInt() throws IOException {
            int c = read();
            while (c <= 32) c = read();
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            int val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
        long nextLong() throws IOException {
            int c = read();
            while (c <= 32) c = read();
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            long val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}
