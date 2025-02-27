import java.util.*;

class Solution {
    // n이 10만으로 2중배열은 안됨
    Map<Integer,ArrayList<Integer>> graphMap = new HashMap<>();
    Map<Integer, Integer> checkMap = new HashMap<>();

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        // map에 노드 추가
        for (int i = 0; i < roads.length; i++) {
            if (!graphMap.containsKey(roads[i][0])) {
                graphMap.put(roads[i][0], new ArrayList<>());
            }

            if (!graphMap.containsKey(roads[i][1])) {
                graphMap.put(roads[i][1], new ArrayList<>());
            }
            graphMap.get(roads[i][0]).add(roads[i][1]);
            graphMap.get(roads[i][1]).add(roads[i][0]);
        }

        for (int i = 0; i < sources.length; i++) {
            checkMap.put(sources[i], i);
        }



        return bfs(sources, destination);
    }

    // 역으로 destination에서 끝노드까지 탐색하면서 source 가 있다면 그때그때 추가하는 방식
    private int[] bfs(int[] sources, int destination) {
        int[] answer = new int[sources.length];
        Arrays.fill(answer, -1);
        int idx = 0;

        // 방문 체크
        Set<Integer> set = new HashSet<>();


        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {destination, 0});
        set.add(destination);

        while (!q.isEmpty()) {
            int[] src = q.poll();

            // 목적지에 다다르면 비용 저장
            if (checkMap.containsKey(src[0])) {
                answer[checkMap.get(src[0])] = src[1];
            }

            // 시작 지점 노드 리스트 찾기
            ArrayList<Integer> list = graphMap.get(src[0]);
            if (list == null) continue;

            // BFS 탐색
            for (int region : list) {
                if (!set.contains(region)) {
                    q.add(new int[]{region, src[1] + 1});
                    set.add(region);
                }
            }
        }

        return answer;

    }
}
