import java.util.*;

class Solution {
    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if (cacheSize == 0) return cities.length * 5;
        
        Map<String, Boolean> cache = new LinkedHashMap<>(cacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Boolean> eldest) {
                return size() > cacheSize;
                
            }
        };
        
        for (String n : cities) {
            String name = n.toLowerCase();
            if (cache.containsKey(name)) {
                answer++;
                cache.get(name);
            } else {
                answer += 5;
                cache.put(name, true);
            }
        }
        return answer;
    }
}