import java.util.*;

class Solution {
    static int END_TIME = (23 * 60) + 59;
    // HH:MM은 00:00부터 23:59까지 주어집니다.
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> map = new HashMap<>(); // 차량번호, 주차된 시간
        Map<String, Integer> in = new HashMap<>(); // 차량 번호, 입차된 시간
        
        
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        
        // in에 남아있다면 23:59에 출차된것으로 처리 
        
        for (String rec : records) {
            
            String[] info = rec.split(" ");
            String[] times = info[0].split(":");
            int minute = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
            
            String carNumber = info[1];
            
            // 입차
            if (info[2].equals("IN")) {
                in.put(carNumber, minute);
            // 출차
            } else {
                int time = in.get(carNumber);
                int sum = (map.getOrDefault(carNumber, 0) + minute - time);
                in.remove(carNumber);
                map.put(carNumber, sum);
            }
        }
        
        
        // 출차가 없는 차량들 23:59로 처리 
        for (String carNumber : in.keySet()) {
            int time = in.get(carNumber);
            int sum = (map.getOrDefault(carNumber, 0) + END_TIME - time);
            map.put(carNumber, sum);
        }
    
        
        TreeSet<String> ts = new TreeSet<>();
        
        for (String carNumber : map.keySet()) {
            ts.add(carNumber);
        } 
        
        
        int[] answer = new int[map.size()];
        int idx = 0;
        while (!ts.isEmpty()) {
            String carNum = ts.pollFirst();
            
            int time = map.get(carNum);
            int sum = 0;
            
            sum += baseFee;
            time -= baseTime;
            
            if (time > 0) {
                int cnt = time / unitTime;
                int rest = time % unitTime; 
                cnt += rest > 0 ? 1 : 0; // 나머지가 0 이상이면 올림 
                sum += cnt * unitFee;
            }
            answer[idx++] = sum;
            
        }
        
        
        return answer;
    }
}