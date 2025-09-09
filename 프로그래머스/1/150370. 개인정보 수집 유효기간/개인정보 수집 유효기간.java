import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] target = Arrays.stream(today.split("\\.")).mapToInt(Integer::parseInt).toArray();
        Map<String, Integer> term = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        
        for (int i = 0; i < terms.length; i++) {
            String[] split = terms[i].split(" ");
            term.put(split[0], Integer.parseInt(split[1]));
        }
        
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            
            String[] dateStr = privacy[0].split("\\.");
            
            int[] date = new int[3];
            
            for (int j = 0; j < 3; j++) {
                date[j] = Integer.parseInt(dateStr[j]);
            }
            
            int period = term.get(privacy[1]);
            date[1] += period;
            
            // 12월을 넘을 경우 
            if (date[1] > 12) {
                date[0] += date[1] / 12;
                date[1] -= 12 * (date[1] / 12);
                if (date[1] == 0) {
                    date[0]--;
                    date[1] = 12;
                }
            }
            
            date[2]--;
            
            if (date[2] == 0) {
                date[2] = 28;
                date[1]--;
                
                if (date[1] == 0) {
                    date[1] = 12;
                    date[0]--;
                }
            }
            
            if (target[0] > date[0]) {
                list.add(i+1);    
            }
            
            if (target[0] == date[0] && target[1] > date[1]) {
                list.add(i+1);
            } 
            
            if (target[0] == date[0] && target[1] == date[1] && target[2] > date[2]) {
                list.add(i+1);
            }
        }
        
        
        return list.stream().mapToInt(x -> x).toArray();
    }
}