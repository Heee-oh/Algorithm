import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        parent = IntStream.range(0, v + 1).toArray();
        // 가중치가 가장 적은 순으로
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new int[]{a, b, c});
        }

        int cost = 0;

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            if (find(node[0]) != find(node[1])) {
                union(node[0], node[1]);
                cost += node[2];
            }
        }


        System.out.println(cost);
    }


    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

}