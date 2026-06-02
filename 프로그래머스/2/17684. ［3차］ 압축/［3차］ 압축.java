import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> list = new ArrayList<>();
        
        // 1. 초기 사전 생성
        int idx = 1;
        Map<String, Integer> map = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            map.put(String.valueOf(c), idx++);
        }
        
        int l = 0, r = 1; // 투포인터로 가장 긴 무자열 찾기 
        String prev = ""; 
        int prevIdx = 0;
        
        while (r <= msg.length()) {
            
            String find = msg.substring(l, r);
            
            if (map.containsKey(find)) {
                prev = find;
                prevIdx = map.get(find);
                r++;
                
            } else {
                list.add(prevIdx);
                map.put(find, idx++);
                prevIdx = 0;
                l = r - 1;
            }
            
        }
        
        if (prevIdx != 0) {
            list.add(prevIdx);
        }
        
        
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}