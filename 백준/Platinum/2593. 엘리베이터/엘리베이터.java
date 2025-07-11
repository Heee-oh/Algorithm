import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] dist, parent;

    static List<Integer>[] elevators; // idx = 엘레베이터, element = 각 층
    static List<Integer>[] floors; // idx = 층 , element = 가능한 엘레베이터

    static final int MAX = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int elevator, dist;

        public Node(int elevator, int dist) {
            this.elevator = elevator;
            this.dist = dist;
        }


        @Override
        public int compareTo(Node o) {
            return dist - o.dist;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        elevators = new List[M + 1];
        floors = new List[N + 1];

        dist = new int[M + 1];
        parent = new int[M + 1];
        Arrays.fill(dist, MAX);

        for (int i = 0; i < M + 1; i++) {
            elevators[i] = new ArrayList<>();
        }

        for (int i = 0; i < N + 1; i++) {
            floors[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 각 층 및 엘레베이터 추가
            for (int j = x; j <= N; j+= y) {
                elevators[i].add(j);
                floors[j].add(i);
            }
        }

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        dijkstra(A, B);



    }

    private static void dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 시작 위치가 있는 엘레베이터는 바로 현재 층이므로 거리가 1
        for (int elevator : floors[start]) {
            pq.offer(new Node(elevator, 1));
            parent[elevator] = -1; // -1로 시작지점을 표시
            dist[elevator] = 1;
        }


        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (dist[current.elevator] < current.dist) continue;

            // 현재 엘레베티어에 있는 모든 층을 꺼낸다.
            for (int floor : elevators[current.elevator]) {

                // 꺼낸 각각의 층이 탈 수 있는 모든 엘레베이터를 꺼낸다.
                for (int i = 0; i < floors[floor].size(); i++) {
                    int elevator = floors[floor].get(i);
                    // 해당 엘레베이터로 가는 비용이 현재 엘베를 갈아타고 가는게 비용이 더 적다면 갱신
                    if (dist[elevator] > current.dist + 1) {
                        dist[elevator] = current.dist + 1;
                        parent[elevator] = current.elevator;
                        pq.offer(new Node(elevator, dist[elevator]));
                    }

                }
            }
        }

        int min = MAX;
        int minElevator = 0;
        for (int elevator : floors[end]) {
            if (dist[elevator] < min) {
                min = dist[elevator];
                minElevator = elevator;
            }
        }

        if (min == MAX) {
            System.out.println(-1);

        }else {
            System.out.println(min);

            Stack<Integer> stack = new Stack<>();
            int cur = minElevator;
            while (cur != -1) {
                stack.push(cur);
                cur = parent[cur];
            }

            while (!stack.isEmpty()) {
                System.out.println(stack.pop());
            }
        }
    }

}