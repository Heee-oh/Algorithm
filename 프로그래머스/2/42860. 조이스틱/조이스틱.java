import java.util.*;

class Solution {

    // 그래프 (A를 제외한 그래프 생성) [인덱스 정보를 가짐]
    // 현재 알파벳 위치에서 다음 알파벳 가는데 비용 계산

    class Alpha {
        int next, cost;

        public Alpha(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    int min = 1000000;

    ArrayList<Alpha>[] graph;
    boolean[] visited;

    public int solution(String name) {
        int len = name.length();
        graph = new ArrayList[len];
        visited = new boolean[len];

        int sum = 0; // 한 위치의 알파벳 최소 비용 합
        for (int i = 0; i < len; i++) {
            // A부터 가는 방법, A뒤로 Z부터 가는 법 중 최소 비용
            sum += Math.min(name.charAt(i) - 'A'
                    , ('Z' - name.charAt(i)) + 1);

            // 리스트 생성
            graph[i] = new ArrayList<>();
        }


        // 거리 값 저장
        // 다음거 - 현재 인덱스 vs 글자 최대 길이 + 현재 알파벳인덱스 - 다음 알파벳 인덱스     
        for (int i = 0; i < len; i++) {
            if (i != 0 && name.charAt(i) == 'A') continue;

            for (int j = 0; j < len; j++) {
                if (name.charAt(j) != 'A' && i != j) {
                    int minCost = Math.min((j - i + len) % len, (i - j + len) % len); // 왼쪽 이동 , 오른쪽 이동 최솟값
                    graph[i].add(new Alpha(j, minCost));
                }
            }
            Collections.sort(graph[i], (o1, o2) -> o1.cost - o2.cost);
        }

        visited[0] = true;
        dfs(0, 0, 0);
        
        return sum + min;
    }


    private void dfs(int idx, int cost, int depth) {

        int cnt = 0;

        if (depth == graph[0].size()) {
            min = Math.min(min, cost);
            return;
        }

        for (int i = 0; i < graph[idx].size(); i++) {
            Alpha alpha = graph[idx].get(i);

            if (!visited[alpha.next]) {
                visited[alpha.next] = true;
                dfs(alpha.next, cost + alpha.cost, depth + 1);
                visited[alpha.next] = false;
                cnt++;
            }
        }
    }


}
