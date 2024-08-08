import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int cur, cost;

        public Node(int next, int cost) {
            this.cur = next;
            this.cost = cost;
        }
    }
    static boolean[] visited;
    static int answer = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n >= k) {
            System.out.println(n - k);
            return;
        }

        int index = (k != 0 && n != 0) ? (k / n) * n + (n + 1) :
                n == 0 && k != 0 ? (k * 2) + 1 : 0;

        visited = new boolean[index];


        bfs(n, k);
        System.out.println(answer);

    }

    private static void bfs(int start, int k) {
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (visited[node.cur]) continue;

            if (node.cur != k) {

                visited[node.cur] = true;

                int nextNode2 = node.cur * 2;
                if (nextNode2 < visited.length && !visited[nextNode2]) {
                    q.add(new Node(nextNode2, node.cost));
                }

                int nextNode = node.cur - 1;
                if (nextNode < visited.length && nextNode >= 0 && !visited[nextNode]) {
                    q.add(new Node(nextNode, node.cost + 1));

                }

                int nextNode1 = node.cur + 1;
                if (nextNode1 < visited.length && !visited[nextNode1]) {
                    q.add(new Node(nextNode1, node.cost + 1));

                }



            } else {
                answer = node.cost;
                return;
            }
        }
    }

}