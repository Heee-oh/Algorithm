import java.util.*;
import java.util.stream.IntStream;
class Solution {
    
    int[] parent;
    int final_cost;
    
    public int solution(int n, int[][] costs) {
        
        // 간선 비용 오름차순 정렬
        Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));
        // 부모 노드 초기화 (섬이 n개이므로 0~n-1번까지)
        parent = IntStream.range(0, n).toArray();
        
        // 낮은 비용부터 크루스칼 알고리즘 진행
        for (int i = 0; i < costs.length; i++) {
            if (find(costs[i][0]) != find(costs[i][1])) { // 부모가 같으면 사이클이 생기기에 방지
                union(costs[i][0], costs[i][1]);
                final_cost += costs[i][2]; 
            }
        }
    
        return final_cost;
    }
    
    // 사실상 두 집합이 합쳐진 것이 아니라 각 집합의 대표 부모가 다른 부모로 편입되는 것
    private void union(int a, int b) {
        a = find(a); // 부모 찾기 
        b = find(b);
        
        // 더 작은게 부모가 된다. 
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
    
    // 부모가 누구인지 찾음
    private int find(int x) {
        if (parent[x] == x) return x; // 서로소 집합으로 부모는 자기 자신을 가리킴
        return parent[x] = find(parent[x]); // 경로 압축 
    }
}