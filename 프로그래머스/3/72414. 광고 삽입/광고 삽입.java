import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = parseTime(play_time);
        int advTime = parseTime(adv_time);
        
        int[] timeLine = new int[playTime + 2];
        
        for (int i = 0; i < logs.length; i++) {
            String[] log = logs[i].split("-");
            
            int start = parseTime(log[0]);
            int end = parseTime(log[1]);
            
            timeLine[start]++;
            timeLine[end]--;
        }
        
        // 타임라인 누적합 생성 
        for (int i = 1; i < timeLine.length; i++) {
            timeLine[i] += timeLine[i-1];
        }
        
        // 누적합
        long[] pSum = new long[playTime + 2];
        pSum[0] = timeLine[0];
        for (int i = 1; i < pSum.length; i++) {
            pSum[i] = pSum[i-1] + timeLine[i];
        }
        
        long max = 0;
        int maxTimeLine = 0;
        for (int i = 0; i < pSum.length - advTime; i++) {
            // 0인 부분은 최댓값이 나올 수 없음
            if (i == 0) {
                if (max < pSum[i + advTime - 1]) {
                    max = pSum[i + advTime - 1];
                    maxTimeLine = i;
                }
            } else {
                if (max < pSum[i + advTime - 1] - pSum[i - 1]) {
                    max = pSum[i + advTime - 1] - pSum[i - 1];
                    
                    maxTimeLine = i;
                }
            }
        }
        
        return parseTime(maxTimeLine);
    }
    
    private int parseTime(String time) {
        String[] part = time.split(":");
        return Integer.parseInt(part[0]) * 3600 + Integer.parseInt(part[1]) * 60 + Integer.parseInt(part[2]);
    }
    
    private String parseTime(int time) {
        StringBuilder sb = new StringBuilder();
        
        int h = time / 3600;
        int m = (time - (h * 3600)) / 60;
        int s = (time - (h * 3600) - (m * 60));
        sb.append(h < 10 ? "0" + h : h).append(":");
        sb.append(m < 10 ? "0" + m : m).append(":");
        sb.append(s < 10 ? "0" + s : s);
        
        return sb.toString();
    }
}