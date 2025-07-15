import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int dist(Node cur) {
            return Math.abs(x - cur.x) + Math.abs(y - cur.y);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    static final int MAX_DISTANCE = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {

            int n = Integer.parseInt(br.readLine()); // 편의점 개수

            Node[] arr = new Node[n + 2];
            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[i] = new Node(x, y);
            }

            Node festival = arr[n + 1]; // 페스티발 위치
            Queue<Node> q = new LinkedList<>(); // 현재 위치를 담을 큐
            boolean[] visited = new boolean[n + 2]; // 방문 여부
            q.add(arr[0]);

            while (!q.isEmpty()) {
                Node current = q.poll();

                // 현재 위치에서 페스티발에 갈 수 있다면 이동하고 종료
                if (festival.dist(current) <= MAX_DISTANCE) {
                    visited[n+1] = true;
                    break;
                }

                // 완전탐색
                for (int i = 1; i <= n; i++) {
                    int dist = arr[i].dist(current);
                    if (!visited[i] && dist <= MAX_DISTANCE) {
                        visited[i] = true;
                        q.add(arr[i]);
                    }
                }
            }


            if (visited[n + 1]) {
                sb.append("happy\n");
            } else {
                sb.append("sad\n");
            }
        }

        System.out.print(sb.toString());



    }
}
