import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        Map<String, Set<String>> map = new HashMap<>();
        Map<String, Integer> idxMap = new HashMap<>();
        
        
        for (int i = 0; i < id_list.length; i++) {
            map.put(id_list[i], new HashSet<>());
            idxMap.put(id_list[i], i);
        }
        
        
        
        for (int i = 0; i < report.length; i++) {
            String[] str = report[i].split(" ");
            
            Set<String> set = map.get(str[1]);
            set.add(str[0]);
            
        }
        
        for (String id : map.keySet()) {
            // k미만 신고당했다면 넘어감
            if (map.get(id).size() < k) continue;
            
            Set<String> set = map.get(id);
            // 신고한 사람들의 집합을 순회하면서 메일을 전송해줌
            for (String name : set) {
                int idx = idxMap.get(name);
                answer[idx]++;
            }
        }
        
        
        return answer;
    }
}