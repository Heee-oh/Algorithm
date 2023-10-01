class Solution {
    public int solution(String s) {
        int answer = 0;
        int count_x = 1;
        int not_x = 0;
        int i = 0;
        int j = i+1;
        
        if(s.length() == 1) return 1;
        
        while(j < s.length()) {
            char x = s.charAt(i);
            
            if(x == s.charAt(j)) {
                count_x++;
                j++;
                
            }else{
                not_x++;
                j++;
                
            }
            
            if(count_x == not_x) {
                answer++;
                i = j++;
                if(j >= s.length()) j--;
                count_x = 1;
                not_x = 0;
            }
      
        }
        
        if(count_x > 1 || not_x > 0) answer++;
        
        return answer;
    }
}