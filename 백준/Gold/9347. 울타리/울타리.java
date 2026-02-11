import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int T = fr.nextInt();



        while (T-- > 0) {
            int R = fr.nextInt();
            int C = fr.nextInt();

            int N = R * C;
            int INF = 1_000_000_000;
            int[] dist = new int[N];
            Arrays.fill(dist, INF);

            boolean[][] map = new boolean[R][C];


            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    map[i][j] = fr.nextInt() == 1;
                }
            }

            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 0; i < R; i++) {
                int left = i * C;
                int right = i * C + (C - 1);

                int dl = map[i][0] ? 1 : 0; // 왼쪽 거리값
                if (dl < dist[left]) {
                    dist[left] = dl;
                    if (dl == 0) dq.addFirst(left);
                    else dq.addLast(left);
                }

                int drt = map[i][C - 1] ? 1 : 0;
                if (drt < dist[right]) {
                    dist[right] = drt;
                    if (drt == 0) dq.addFirst(right);
                    else dq.addLast(right);
                }
            }

            for (int j = 0; j < C; j++) {
                int top = j;
                int bottom = (R - 1) * C + j;

                int dt = map[0][j] ? 1 : 0;
                if (dt < dist[top]) {
                    dist[top] = dt;
                    if (dt == 0) dq.addFirst(top);
                    else dq.addLast(top);
                }

                int db = map[R - 1][j] ? 1 : 0;
                if (db < dist[bottom]) {
                    dist[bottom] = db;
                    if (db == 0) dq.addFirst(bottom);
                    else dq.addLast(bottom);
                }
            }

            while (!dq.isEmpty()) {
                int cur = dq.pollFirst();
                int r = cur / C;
                int c = cur % C;
                int curDist = dist[cur];

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                    int nxt = nr * C + nc;
                    int w = map[nr][nc] ? 1 : 0;
                    int nDist = curDist + w;

                    if (nDist < dist[nxt]) {
                        dist[nxt] = nDist;

                        if (w == 0) dq.addFirst(nxt);
                        else dq.addLast(nxt);
                    }
                }
            }



            // 결과: 꽃(0) 칸만
            int max = -1, cnt = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (!map[r][c]) { // 꽃 칸
                        int d = dist[r * C + c];
                        if (d > max) { max = d; cnt = 1; }
                        else if (d == max) cnt++;
                    }
                }
            }

            System.out.println(max + " " + cnt);

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
