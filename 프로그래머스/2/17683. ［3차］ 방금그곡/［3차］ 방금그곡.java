import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        
        String answer = "";
        int maxLen = 0; // 정답 후보의 재생 시간
        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");
            
            // 멜로디 분할 
            List<String> list = new ArrayList<>();
            Stack<Character> stack = new Stack<>();
            for (int j = 0; j < info[3].length(); j++) {
                char tmp = info[3].charAt(j);
                if (j + 1 < info[3].length() && info[3].charAt(j + 1) == '#') {
                    list.add(tmp + "#");
                    j++;
                } else {
                    list.add(tmp+"");
                }
                
            }
            
            String[] start = info[0].split(":");
            String[] end = info[1].split(":");
            int startAt = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int endAt = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
            String name = info[2];
            String melody = info[3];
            
            // 재생 길이에 대한 총 멜로디 재생 구하기 
            int len = endAt - startAt; // 해당 곡의 재생 시간 
            int melodyLen = list.size();
            
            StringBuilder sb = new StringBuilder();
            for (int t = 0; t < len; t++) {
                sb.append(list.get(t % melodyLen));
            }
            
            // 마지막이 #이 아니라면 
            if (m.charAt(m.length() - 1) != '#') {
                String tmp = sb.toString().replaceAll(m + '#', " "); // 미리 #인 곳을 없애놓음 
                sb.delete(0, sb.length());
                sb.append(tmp);
            }
           
            
            // 가장 길면서 앞에 오는 걸로 갱신 
            if (maxLen < len && sb.indexOf(m) != -1) {
                answer = name;
                maxLen = len;
            }
            
        } 
        

        return answer.isBlank() ? "(None)" : answer;
    }
}