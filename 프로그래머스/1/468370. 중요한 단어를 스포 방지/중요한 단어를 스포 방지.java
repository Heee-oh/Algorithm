import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        
        List<String> text = new ArrayList<>();
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        // 스포방지 밖에 있는 단어
        Set<String> outside = new HashSet<>();
        // 중요한 단어 
        Set<String> used = new HashSet<>();
        
         int n = message.length(), i = 0;
        
         while (i < n) {
            if (message.charAt(i) == ' ') { i++; continue; }
            int s = i;
            while (i < n && message.charAt(i) != ' ') i++;
            text.add(message.substring(s, i));
            starts.add(s);
            ends.add(i - 1);
        }
        
        int m = text.size();
        int[] reveal = new int[m];
        
          for (i = 0; i < m; i++) {
            int last = 0; // 스포방지단어 넘버링 
              
            for (int j = 0; j < spoiler_ranges.length; j++) {
                int s = spoiler_ranges[j][0], e = spoiler_ranges[j][1];
                
                // 현재 텍스트 구간에 스포방지 구간이 포함되는 경우 
                if (Math.max(starts.get(i), s) <= Math.min(ends.get(i), e)) last = j + 1;
            }
            reveal[i] = last; // last 클릭시 해당 단어가 완전히 공개됨
              
              // last == 0이면 이는 스포방지단어가 아님 
            if (last == 0) outside.add(text.get(i));
        }

        
        for (int t = 1; t <= spoiler_ranges.length; t++) {
            for (i = 0; i < m; i++) {
                if (reveal[i] != t) continue; // 각 단어의 클릭에 맞게 실행 
                String w = text.get(i);
                if (outside.contains(w) || used.contains(w)) continue;
                used.add(w);
                answer++;
            }
        }
        
        return answer;
    }
}