import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static class Point implements Comparable<Point>{
        int v;
        int idx;

        public Point(int v, int idx) {
            this.v = v;
            this.idx = idx;
        }

        @Override
        public int compareTo(Point o) {
            return Long.compare(v, o.v);
        }
    }

    static int[] parent;

    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = IntStream.range(0, n).toArray();
        Point[][] planets = new Point[3][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets[0][i] = new Point(x, i);
            planets[1][i] = new Point(y, i);
            planets[2][i] = new Point(z, i);

        }

        for (int i = 0; i < 3; i++) {
            Arrays.sort(planets[i]);
            createEdge(n, planets[i]);
        }

        long answer = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            if (cnt == n-1) break;

            int[] info = pq.poll();
            int v1 = info[1];
            int v2 = info[2];

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

            pq.add(new int[]{Math.abs(p1.v - p2.v), p1.idx, p2.idx});

        }
    }

}
