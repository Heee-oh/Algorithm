import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    // 양방향 노드, 1부터 시작, M개의 간선
    // 0이상의 정수 X를 고르고 광장 1부터 거리가 X이하(1도 포함)인 모두를 지하도로 연결 (최단경로)
    // 지하도를 설치하는데 드는 전체 비용 C * X

    // 지하도로 연결된 광장들을 잇는 모든 도로 철거 (비용 X)
    // 철거되지 않은 남은 도로 전부 보수, 길이 d 도로의 보수 비용 d

    static class Node {
        int to;
        long dist;

        public Node(int to, long dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static int N, M, C;
    static List<Node>[] graph;

    static int[] parent;
    static long[] dist;
    static final long INF = (long) 4e18;

    static long answer = INF;
    static long repairSum = 0; // "남아있는 도로 보수 비용 합" (동적으로 줄어듦)

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        N = fr.nextInt();
        M = fr.nextInt();
        C = fr.nextInt();

        parent = IntStream.range(0, N + 1).toArray();
        graph = new List[N + 1];
        dist = new long[N + 1];

        Arrays.fill(dist, INF);
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int Ai = fr.nextInt();
            int Bi = fr.nextInt();
            int Di = fr.nextInt();
            repairSum += Di;

            graph[Ai].add(new Node(Bi, Di));
            graph[Bi].add(new Node(Ai, Di));
        }

        dijkstra();

        System.out.println(answer);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.dist, o2.dist));
        boolean[] visited = new boolean[N + 1];
        pq.offer(new Node(1, 0));
        dist[1] = 0;

        answer = Math.min(answer, repairSum);

        while (!pq.isEmpty()) {
            Node cur = pq.poll(); //
            int u = cur.to;
            long d = cur.dist;

            if (visited[u]) continue;
            if (d != dist[u]) continue;

            visited[u] = true; // 위의 조건식을 통과했다 = 최단경로다.

            // 따라서 최단경로로 나온 u와 연결된 이미 지하도를 연결한 집합과 관련된 노선 철거
            for (Node e : graph[u]) {
                if (visited[e.to]) {
                    repairSum -= e.dist;
                }
            }

            answer = Math.min(answer, dist[u] * C + repairSum);

            for (int i = 0; i < graph[cur.to].size(); i++) {
                Node next = graph[cur.to].get(i);

                if (!visited[next.to] && dist[next.to] > dist[cur.to] + next.dist) {
                    dist[next.to] = dist[cur.to] + next.dist;

                    pq.offer(new Node(next.to, dist[next.to]));
                }
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
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            int c = read();
            while (c <= 32) c = read(); // 특수문자 공백등 무시
            int sign = 1;
            if (c == '-') {sign = -1; c = read();}
            int val = 0;

            while (c > 32) { // 숫자면 계속 읽음
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }


}
