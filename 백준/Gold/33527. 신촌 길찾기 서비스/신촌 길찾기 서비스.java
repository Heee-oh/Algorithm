import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int N = fr.nextInt();
        int X = fr.nextInt();

        int M = X * 5;
        int[][] dist = new int[M][M];
        int[][] map = new int[N][5];

        for (int i = 0; i < M; i++) {
            Arrays.fill(dist[i], 1000000);
            dist[i][i] = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = fr.nextInt();
            }

            // 모든 정류장이 다른 정류장과 연결
            for (int j = 0; j < 5; j++) {
                for (int k = j + 1; k < 5; k++) {
                    int u = j * X + map[i][j] - 1;
                    int v = k * X + map[i][k] - 1;

                    dist[u][v] = 1;
                    dist[v][u] = 1;
                }
            }
        }


        for (int k = 0; k < M; k++) {
            for (int a = 0; a < M; a++) {
                for (int b = 0; b < M; b++) {
                    dist[a][b] = Math.min(dist[a][b], dist[a][k] + dist[k][b]);
                }
            }
        }

        int Q = fr.nextInt();
        for (int i = 0; i < Q; i++) {
            int u = fr.nextInt() - 1;
            int v = fr.nextInt() - 1;
            int min = (int) 1e6;

            // 각 대학의 시작점 노드id값을 구함
            for (int j = 0; j < 5; j++) {
                int su = j * X + map[u][j] - 1;


                for (int k = 0; k < 5; k++) {
                    int ev = k * X + map[v][k] - 1;

                    min = Math.min(min, dist[su][ev] + 1);

                }
            }

            sb.append(min == 1e6 ? -1 : min).append("\n");


        }

        System.out.print(sb.toString());
    }

    static class FastReader {
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
    }
}
