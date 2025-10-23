import java.util.*;

class Solution {
    // 도착 시간중 제일 늦은 시각
    //같은 시각에 도착한 크루 중 대기열에서 제일 뒤
    // 23:59분 전까지 
    
    public String solution(int n, int t, int m, String[] timetable) {
        StringBuilder sb = new StringBuilder();
        
        // 시간순으로 정렬
        int[] time = new int[timetable.length];
        
        for (int i = 0; i < time.length; i++) {
            String[] tmp = timetable[i].split(":");
            time[i] = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
        }
        Arrays.sort(time);
        
        // 9시 시작 
        int start = 9 * 60;
        int end = 23 * 60 + 59;
        
        int idx = 0;
        // n회만큼 셔틀 운행
        for (int i = 0; i < n; i++) {
            TreeSet<Integer> ts = new TreeSet<>();
            int cnt = 0;
            
            // 현재 운행 버스의 출발시간안에 도착한 크루들 인원 카운트
            while (idx < time.length && start >= time[idx]) {
                if (cnt == m) break;
                ts.add(time[idx]);
                cnt++;
                idx++;
            }
            
            // 버스에 다 탔지만 막차였다면 
            if (cnt == m && i == n - 1) {
                // 동시 시간대 사람만 있다면 
                if (ts.size() == 1) {
                    int answer = ts.pollFirst() - 1; // 1분먼저 나와서 탄다. 
                    sb.append(String.format("%02d:%02d", (answer/60), (answer%60)));
                    
                } else {
                    
                    int answer = ts.pollLast() - 1;
                    sb.append(String.format("%02d:%02d", (answer/60), (answer%60)));
                }
                
                break;
            }
            
            // 막차인데 버스에 자리가 남는다면
            if (cnt < m && i == n - 1) {
                sb.append(String.format("%02d:%02d", (start/60), (start%60)));
            }
            
            // 다음 차 시간으로 이동
            start += t;
        }     
        
        
        return sb.toString();
    }
}