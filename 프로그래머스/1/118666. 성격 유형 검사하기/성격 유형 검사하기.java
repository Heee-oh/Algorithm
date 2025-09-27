import java.util.*;

class Solution {

    // 1 7 : 3
    // 2 6 : 2
    // 3 5 : 1
    // 4 : 0
    
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        int[] score = new int[8];
        char[] types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        
        score[1] = score[7] = 3;
        score[2] = score[6] = 2;
        score[3] = score[5] = 1;
        
        Map<Character, Integer> cnt = new HashMap<>();
        
        for (int i = 0; i < survey.length; i++) {
            char[] type = survey[i].toCharArray();
            
            if (choices[i] < 4) {
                int newScore = cnt.getOrDefault(type[0], 0) + score[choices[i]];
                cnt.put(type[0], newScore);
                
            } else if (choices[i] > 4) {
                int newScore = cnt.getOrDefault(type[1], 0) + score[choices[i]];
                cnt.put(type[1], newScore);
            } 
        }
        
        
        
        for (int i = 0; i < types.length; i+= 2) {
            char type1 = types[i];
            char type2 = types[i + 1];
            
            int rs1 = cnt.getOrDefault(types[i], -1);
            int rs2 = cnt.getOrDefault(types[i + 1], -1);
            
            if (rs1 >= rs2) {
                answer += types[i];
            } else {
                answer += types[i+1];
            }
            
        }
        return answer;
    }
}