import java.util.Map;
import java.util.HashMap;

class Solution {
    Map<Long, Long> map;
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        map = new HashMap<>(); // 값이 10^12이므로 map으로 해결 
        
        for (int i = 0; i < room_number.length; i++) {
            long roomReq = room_number[i];
            long allocatedRoom = find(roomReq); // find로 할당 룸을 찾으면서 경로 압축 
            
            map.put(allocatedRoom, allocatedRoom + 1); // 할당후 항상 다음을 가리키게 함 
            answer[i] = allocatedRoom;
        }
        return answer;
    }
    
    // 서로소 집합으로 해결 
    private long find(long x) {
        if (!map.containsKey(x)) return x;
        
        long parent = find(map.get(x));
        map.put(x, parent);
        
        return parent;
        
    }
}
