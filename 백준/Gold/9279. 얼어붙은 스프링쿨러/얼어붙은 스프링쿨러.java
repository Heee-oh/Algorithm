import java.io.*;

public class Main {
    // N의 최대값이 1000이므로 넉넉하게 1005로 설정
    static final int MAX_N = 1005;
    static final int INF = 1_000_000_000;

    // 인접 리스트를 배열로 구현 (Static 메모리)
    static int[] head = new int[MAX_N];
    static int[] to = new int[MAX_N * 2];
    static int[] weight = new int[MAX_N * 2];
    static int[] next = new int[MAX_N * 2];
    static int edgeIdx;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();

        while (true) {
            String firstToken = fr.next();
            if (firstToken == null) break;

            int N = Integer.parseInt(firstToken);
            int C = fr.nextInt();

            // 초기화: head 배열만 -1로 리셋 (객체 생성 X)
            for (int i = 1; i <= N; i++) head[i] = -1;
            edgeIdx = 0;

            for (int i = 0; i < N - 1; i++) {
                int u = fr.nextInt();
                int v = fr.nextInt();
                int w = fr.nextInt();
                addEdge(u, v, w);
                addEdge(v, u, w);
            }

            // 결과 계산 (루트의 부모 비용은 INF로 설정)
            sb.append(dfs(C, -1, INF)).append("\n");
        }
        System.out.print(sb);
    }

    static void addEdge(int u, int v, int w) {
        to[edgeIdx] = v;
        weight[edgeIdx] = w;
        next[edgeIdx] = head[u];
        head[u] = edgeIdx++;
    }

    private static long dfs(int u, int p, int costToParent) {
        long childSum = 0;
        boolean isLeaf = true;

        for (int i = head[u]; i != -1; i = next[i]) {
            int v = to[i];
            if (v == p) continue;

            isLeaf = false;
            childSum += dfs(v, u, weight[i]);
        }

        // 리프 노드라면 부모와 연결된 간선 가중치 반환
        if (isLeaf) return costToParent;

        // 내부 노드라면: (부모 간선 끊기) vs (자식들로 가는 모든 경로 끊기) 중 최소값
        return Math.min((long) costToParent, childSum);
    }

    // 메모리 효율을 극대화한 입력 클래스
    static class FastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024 * 16];
        private int ptr = 0;
        private int len = 0;

        private int read() throws IOException {
            if (ptr < len) return buffer[ptr++];
            len = in.read(buffer);
            ptr = 0;
            if (len <= 0) return -1;
            return buffer[ptr++];
        }

        public String next() throws IOException {
            int b = read();
            while (b != -1 && b <= 32) b = read();
            if (b == -1) return null;
            StringBuilder sb = new StringBuilder();
            while (b > 32) {
                sb.append((char) b);
                b = read();
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            int n = 0;
            int b = read();
            while (b != -1 && b <= 32) b = read();
            if (b == -1) return -1;
            while (b > 32) {
                n = n * 10 + (b - '0');
                b = read();
            }
            return n;
        }
    }
}