class Solution {
    public String solution(String s) {
        
        
        s += "#";
        
        String[] str = s.split(" ");
        char temp;
        String answer = "";
        
        // 공백 하나만 있는 입력이 들어오나? 
        
            for (int i = 0; i < str.length; i++) {
                
                if (str[i].isBlank()) {
                    answer += " ";
                    continue;
                }
                
                temp = str[i].charAt(0);
                if (97 <= temp && temp <= 122) {
                    answer += ((char)(temp-32) + str[i].substring(1).toLowerCase());
                } else {
                    answer += str[i].charAt(0) + str[i].substring(1).toLowerCase() ;    
                }
            
                if (i < str.length) {
                    answer += " ";
                }
            }
            
        
            
            

        
        return answer.substring(0, answer.length() - 2);
    }
}