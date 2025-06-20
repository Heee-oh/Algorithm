import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static class Point implements Comparable<Point>{
        long v;
        int idx;

        public Point(long v, int idx) {
            this.v = v;
            this.idx = idx;
        }

        @Override
        public int compareTo(Point o) {
            return Long.compare(v, o.v);
        }
    }

    static int[] parent;

    static PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        parent = IntStream.range(0, n).toArray();

        Point[] soredX = new Point[n];
        Point[] soredY = new Point[n];
        Point[] soredZ = new Point[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());
            soredX[i] = new Point(x, i);
            soredY[i] = new Point(y, i);
            soredZ[i] = new Point(z, i);

        }

        Arrays.sort(soredX); // x 기준 오름차순 정렬
        Arrays.sort(soredY);  // y 기준 오름차순 정렬
        Arrays.sort(soredZ);  // z 기준 오름찬순 정렬

        long answer = 0;
        int cnt = 0;

        createEdge(n, soredX);
        createEdge(n, soredY);
        createEdge(n, soredZ);

        while (!pq.isEmpty()) {
            if (cnt == n-1) break;

            long[] info = pq.poll();
            int v1 = (int) info[1];
            int v2 = (int) info[2];

            if (find(v1) != find(v2)) {
                union(v1,v2);
                answer += info[0];
                cnt++;
            }
        }

        System.out.println(answer);
    }


    private static void union(int a, int b) {

        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void createEdge(int n, Point[] point) {
        for (int i = 1; i < n; i++) {
            Point p1 = point[i];
            Point p2 = point[i - 1];

            pq.add(new long[]{Math.abs(p1.v - p2.v), p1.idx, p2.idx});

        }
    }

}
