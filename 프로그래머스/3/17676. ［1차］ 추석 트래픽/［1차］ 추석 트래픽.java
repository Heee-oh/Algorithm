import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        List<long[]> list = new ArrayList<>();
        
        for (int i = 0; i < lines.length; i++) {
            String[] logDetail = lines[i].split(" ");
            
            String processingTime = logDetail[2].substring(0, logDetail[2].length() - 1);
            long end = toMs(logDetail[1]); // ms로 변화
            long duration = (long)(Double.parseDouble(processingTime) * 1000);
            long start = end - duration + 1;
            
            list.add(new long[] {start, end});
        }
        
        list.sort((a, b) -> Long.compare(a[0],b[0]));
        
        for (int i = 0; i < list.size(); i++) {
            long[] cur = list.get(i);
            long s = cur[0];
            long th = s + 999; // + 1초 - 0.001
            
            int cnt = 0;
            
            for (int j = 0; j < list.size(); j++) {
                long[] tmp = list.get(j);
                
                if (s <= tmp[1] && tmp[0] <= th) {
                    cnt++;
                }
            }
            
            answer = Math.max(answer, cnt);
            
            // 끝 시간대 탐색 
            long e = cur[1];
            th = e + 999;
            cnt = 0;
            for (int j = 0; j < list.size(); j++) {
                long[] tmp = list.get(j);
                
                if (e <= tmp[1] && tmp[0] <= th) {
                    cnt++;
                }
            }
            
            answer = Math.max(answer, cnt);
            
        }
        
        
        return answer;
    }
    
    private long toMs(String time) {
        String[] times = time.split(":");
        
        return (Long.parseLong(times[0]) * 3600 * 1000) 
            + (Long.parseLong(times[1]) * 60 * 1000) 
            + (long)(Double.parseDouble(times[2]) * 1000);
        
    }
}