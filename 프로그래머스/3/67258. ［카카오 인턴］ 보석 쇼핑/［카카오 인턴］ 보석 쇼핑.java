import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {0, gems.length};
        Set<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }
        
        int n = set.size();
        
        Map<String, Integer> map = new HashMap<>();
        int s = 0, e = 0;
        
        while (e <= gems.length) {
            if (map.size() < n) {
                int cnt = map.getOrDefault(gems[e], 0) + 1;
                map.put(gems[e], cnt);
                e++;
                
            } else if (map.size() == n) {
                if (answer[1] - answer[0] + 1 > e - s + 1) {
                    answer[0] = s;
                    answer[1] = e;
                }
                
                
                int cnt = map.get(gems[s]);
                
                if (cnt == 1) {
                    if (e == gems.length) break;
                    int v = map.getOrDefault(gems[e], 0) + 1;
                    map.put(gems[e], v);
                    e++;
                    continue;
                }
                
                map.put(gems[s], cnt-1);
                s++;
            }
        }
        
        if (answer[0] == 0 && answer[1] == gems.length) {
            answer[0] = 1;
        } else {
            answer[0]++;
        }
        
        return answer;
    }
}