import java.util.*;

class Solution {
    public int[] solution(String s) {
        boolean[] num = new boolean[100001];
        
        s = s.substring(1, s.length() - 2);
        String[] str = s.replaceAll("},", " ").replaceAll("[{]", "").split(" ");
        
        Arrays.sort(str, (a, b) -> a.length() - b.length());
        int len = str[str.length -1].length();
        
        Queue<Integer> q = new LinkedList<>();
        
        for (String nums : str) {
            for (String n : nums.split(",")) {
                
                int n1 = Integer.parseInt(n);
                if (!num[n1]) {
                    num[n1] = true;
                    q.offer(n1);
                } 
            }
        }
        
        
        int[] answer = new int[q.size()];
        
        int idx = 0;
        while (!q.isEmpty()) {
            answer[idx++] = q.poll();
            
        }
        
        return answer;
    }
}