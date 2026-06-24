import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    // 숫자 확인 -> 해당 숫자로 이동
    // 이미 열려있다면 중지, 1번 그룹
    // 1번 상자 그룹 밖에 없다면 0점
    // 상자 그룹 속한 상자 수 * 2번 그룹 상자 수 
    int[] parent;
    public int solution(int[] cards) {
        parent = IntStream.range(0, cards.length + 1).toArray();
        boolean[] visited = new boolean[cards.length + 1];
        for (int i = 0; i < cards.length; i++) {
            int n = cards[i];
                
            // 문제 조건에 중복 원소가 없으므로 무조건 사이클이 생김
            while (!visited[n]) {
                visited[n] = true;
                union(i+1, find(n));
                n = cards[n-1];
            }
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 1; i < parent.length; i++) {
            map.put(parent[i], map.getOrDefault(parent[i], 0) + 1);
        }
        
        
        if (map.size() < 2) {
            return 0;
        }
        
        int first = 0, last = 0;
        for (int cnt : map.values()) {
            
            if (first < cnt) {
                last = Math.max(last, first);
                first = cnt;
                
                
            } else if (last < cnt) {
                last = cnt;
            }
        }
        
        return first * last;
    }
    
    
    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a <= b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
        
        
    }
    
    
    
    
    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}