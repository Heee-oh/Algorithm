import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {

    static final long INF = Long.MAX_VALUE;

    static class Node {
        int to;
        long w;

        public Node(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int parity;
        long w;

        public Edge(int to, int parity, long w) {
            this.to = to;
            this.parity = parity;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o) {
            return Long.compare(w, o.w);
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();

        int N = fr.nextInt();
        int M = fr.nextInt();

        // w는 long
        List<Node>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++) {
            int u = fr.nextInt();
            int v = fr.nextInt();
            long w = fr.nextLong();

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        long[][] dist = new long[2][N + 1];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dist[i], INF);
        }

        dist[0][1] = dist[1][1] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0, 0));
        boolean flag = true;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.w > dist[cur.parity][cur.to]) {
                continue;
            }

            for (int i = 0; i < graph[cur.to].size(); i++) {
                Node next = graph[cur.to].get(i);

                long nextW = dist[cur.parity][cur.to] + next.w;
                int ns = (Long.lowestOneBit(nextW) == 1) ? 1 : 0;

                if (dist[ns][next.to] > nextW) {
                    dist[ns][next.to] = nextW;
                    pq.offer(new Edge(next.to, ns, dist[ns][next.to]));
                }
            }

            if (flag) {
                flag = false;
                dist[1][1] = INF;
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(dist[1][i] == INF ? -1 : dist[1][i]).append(" ");
            sb.append(dist[0][i] == INF ? -1 : dist[0][i]).append("\n");
        }

        System.out.print(sb.toString().trim());



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
