import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static class Node implements Comparable<Node> {
        int idx;
        long w;
        int collected;

        public Node(int idx, long w, int collected) {
            this.idx = idx;
            this.w = w;
            this.collected = collected;
        }

        // 거리가 가까운 순, 같다면 조각이 많은 순
        @Override
        public int compareTo(Node o) {
            if (this.w != o.w) return Long.compare(this.w, o.w);
            return Integer.compare(o.collected, this.collected);
        }
    }

    static class Edge {
        int to, w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static long[] dist;
    static long[] paperDist;
    static int[] parent;
    static int[] collect;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        FastReader fr = new FastReader();
        int N = fr.nextInt();
        int M = fr.nextInt();

        List<Edge>[] graph = new ArrayList[N + 1];
        int[] piece = new int[N + 1];
        dist = new long[N + 1];
        paperDist = new long[N + 1];
        parent = new int[N + 1];
        collect = new int[N + 1];

        int paperCnt = 0; // 종이 조각 총 개수


        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int u = fr.nextInt();
            int v = fr.nextInt();
            int w = fr.nextInt();

            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        for (int i = 1; i <= N; i++) {
            piece[i] = fr.nextInt();
            paperCnt += piece[i];
        }


        dijkstra(graph, piece);

        if (collect[N] != paperCnt) {
            System.out.println(-1);
            return;
        }

        int eCnt = 1;
        int num = N;
        List<Integer> answer = new ArrayList<>();
        while (parent[num] != num) {
            answer.add(num);
            num = parent[num];
            eCnt++;
        }
        answer.add(1);

        for (int i = 0; i < answer.size(); i++) {
            int val = answer.get(answer.size() - i - 1);
            sb.append(val).append(" ");
        }

        System.out.println(eCnt);
        System.out.println(sb.toString());

    }

    private static void dijkstra(List<Edge>[] graph, int[] piece) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0, piece[1]));
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        parent[1] = 1;
        collect[1] = piece[1];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.w > dist[cur.idx]) continue;
            if (cur.w == dist[cur.idx] && cur.collected < collect[cur.idx]) continue;

            for (Edge edge : graph[cur.idx]) {
                if (dist[edge.to] > dist[cur.idx] + edge.w) {
                    dist[edge.to] = dist[cur.idx] + edge.w; // 거리 갱신
                    collect[edge.to] = collect[cur.idx] + piece[edge.to];

                    parent[edge.to] = cur.idx;

                    pq.offer(new Node(edge.to, dist[edge.to], collect[edge.to]));

                } else if (dist[edge.to] == dist[cur.idx] + edge.w) {
                    if (collect[edge.to] < collect[cur.idx] + piece[edge.to]) {
                        collect[edge.to] = collect[cur.idx] + piece[edge.to];
                        parent[edge.to] = cur.idx;

                        pq.offer(new Node(edge.to, dist[edge.to], collect[edge.to]));

                    }
                }

            }
        }
    }
//    private static void dijkstra2(List<Node>[] graph, int[] piece) {
//        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
//        pq.offer(new int[] {1, -piece[1]});
//        Arrays.fill(paperDist, Long.MAX_VALUE);
//        paperDist[1] = 0;
//        parent[1] = 1;
//        collect[1] = piece[1];
//
//
//        while (!pq.isEmpty()) {
//            int[] cur = pq.poll();
//            int num = cur[0];
//
//            for (Node node : graph[num]) {
//
//                // 수집 많이 한 순으로 저장
//                if (collect[node.next] > collect[num] + (piece[node.next] * - 1)) {
//                    pq.offer(new int[]{node.next, collect[node.next]});
//
//                    // 수집은 같지만 더 최단경로라면 이동
//                } else if (collect[node.next] == collect[num] + (piece[node.next])) {
//
//                    if (paperDist[node.next] > paperDist[num] + node.w) {
//                        paperDist[node.next] = paperDist[num] + node.w;
//                        parent[node.next] = num;
//                    }
//
//                }
//            }
//        }
//    }



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
