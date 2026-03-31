import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int[] time = new int[timetable.length];
        
        for(int i = 0; i < timetable.length; i++) {
            String[] tmp = timetable[i].split(":");
            time[i] = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
        }
        
        Arrays.sort(time);
        
        int start = 9 * 60;
        int end = 23* 60 + 59;
        int idx = 0;
        
        int ans = 0;
        TreeSet<Integer> ts = new TreeSet<>();
        
        for (int i = 0; i < n; i++) {
            int cnt = 0; // 태운 크루원 수
            // 배차 시간에 있는 크루들 태우기 
            while(idx < time.length) {
                if (cnt >= m || start < time[idx]) break;
                ts.add(time[idx++]);
                cnt++;
            }
            
            // 마지막 셔틀이며, 탈 수 있다면
            if (i == n - 1 && cnt < m) {
                ans = start;
                break;
                
                // 탈 수 없다면 
            } else if (i == n - 1 && cnt == m) {
                ans = ts.pollLast() - 1;
                break;
            }
            
            
            start += t;
            ts.clear();
        }
        
        
        return String.format("%02d:%02d", ans/60, ans % 60);
    }
}