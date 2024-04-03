import java.util.*;

class Solution {
    
    // 1번 부터 순서대로 한사람씩 단어
    // 마지막 다음 1번부터 다시시작
    // 마지막 문자로 시작하는 단어
    // 중복 단어 불가
    // 한 글자 불가
    
    public int[] solution(int n, String[] words) {
        Map<String, Boolean> map = new HashMap<>();
        char lastWord = words[0].charAt(0);
        
        for (int i = 0; i < words.length; i++) {          
            if(map.getOrDefault(words[i], false)) 
                return new int[]{(i % n) + 1 , (int)Math.ceil((float)(i + 1) / n)};
                
            else if (lastWord != words[i].charAt(0)) 
                return new int[]{(i % n) + 1 , (int)Math.ceil((float)(i + 1) / n)};
            
            map.put(words[i], true);
            lastWord = words[i].charAt(words[i].length() - 1);
        }
        
        return new int[]{0,0};
    }
}