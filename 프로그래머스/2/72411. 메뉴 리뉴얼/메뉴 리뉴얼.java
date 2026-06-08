import java.util.*;

class Solution {
    Map<String, Integer>[] map;
    int[] maxLen;
    
    public String[] solution(String[] orders, int[] course) {
        PriorityQueue<String> pq = new PriorityQueue<>();
        maxLen = new int[11]; // 각 코스 개수별 가장 많이 함께 주문한 요리 카운팅 
        
        map = new HashMap[11];
        for (int i = 2; i <= 10; i++) {
            map[i] = new HashMap<>();
        }
        
        for (String order : orders) {
            StringBuilder sb = new StringBuilder();
            
            char[] tmp = order.toCharArray();
            Arrays.sort(tmp);
            sb.append(tmp); // 원소도 오름차순 정렬되어야함 
            
            dfs("", sb.toString(), 0);
        }
        
        for (int size : course) {
            if (maxLen[size] < 2) continue;
            
            for (Map.Entry<String, Integer> entry : map[size].entrySet()) {
                if (maxLen[size] == entry.getValue()) {
                    pq.add(entry.getKey());
                }
            }
        }
        
        
        
        String[] answer = new String[pq.size()];
        int idx = 0;
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll();
        }
        return answer;
    }
    
    private void dfs(String str, String order, int idx) {
        if (str.length() > order.length()) {
            return;    
        }
        
        if (str.length() >= 2) {
            int size = str.length();
            int cnt = map[size].getOrDefault(str, 0) + 1;
            maxLen[str.length()] = Math.max(maxLen[str.length()], cnt);
            map[size].put(str, cnt);
        }
        
        for (int i = idx; i < order.length(); i++) {
            dfs(str + order.charAt(i), order, i + 1);
        }
    }
}