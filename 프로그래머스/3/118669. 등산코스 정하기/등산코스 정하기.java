import java.util.*;

class Solution {

    static class Node {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Node>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int w = path[2];
            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
        }

        Set<Integer> gateSet = new HashSet<>();
        for (int g : gates) gateSet.add(g);

        Set<Integer> summitSet = new HashSet<>();
        for (int s : summits) summitSet.add(s);

        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n1 -> n1.cost));

        for (int g : gates) {
            intensity[g] = 0;
            pq.offer(new Node(g, 0));
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (intensity[curr.to] < curr.cost) continue; // 이미 더 나은 경로로 방문했음
            if (summitSet.contains(curr.to)) continue; // 산봉우리에 도달했으면 더 안 감

            for (Node next : graph[curr.to]) {
                if (gateSet.contains(next.to)) continue; // 다른 게이트로 가면 안 됨

                int newIntensity = Math.max(intensity[curr.to], next.cost);

                if (newIntensity < intensity[next.to]) {
                    intensity[next.to] = newIntensity;
                    pq.offer(new Node(next.to, newIntensity));
                }
            }
        }

        int minSummit = 0;
        int minIntensity = Integer.MAX_VALUE;

        Arrays.sort(summits); // 번호 낮은 산봉우리를 우선하기 위해 정렬

        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minSummit = summit;
                minIntensity = intensity[summit];
            }
        }

        return new int[]{minSummit, minIntensity};
    }
}
