import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Queue<String> q = new LinkedList<>(); // 입장 퇴장 
        Map<String, String> map = new HashMap<>(); // id - 닉네임 맵
        
        for (String command : record) {
            String[] commands = command.split(" ");

            if (commands[0].equals("Enter")) {
                map.put(commands[1], commands[2]);
                q.offer("+" + commands[1]);
            } else if (commands[0].equals("Leave")) {
                q.offer("-" + commands[1]);
            } else {
                map.put(commands[1], commands[2]);
                
            }
            
        }
        
        String[] answer = new String[q.size()];
        int idx = 0;
        while (!q.isEmpty()) {
            String poll = q.poll();
            String id = map.get(poll.substring(1));
            
            if (poll.startsWith("+")) {
                answer[idx] = id+"님이 들어왔습니다.";            
            } else {
                answer[idx] = id+"님이 나갔습니다.";
            }
            idx++;
        }
        
        
        return answer;
    }
}