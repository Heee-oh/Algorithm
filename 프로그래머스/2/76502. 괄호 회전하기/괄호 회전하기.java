import java.util.Stack;

class Solution {
    public int solution(String s) {
        
        int count = 0;
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < s.length(); j++) {
                int index = (i + j) % s.length();
                if (s.charAt(index) == '(' 
                   ||s.charAt(index) == '['
                   ||s.charAt(index) == '{') {
                    
                    stack.push(s.charAt(index));
                    
                } else {
                    if (!stack.isEmpty()) {
                        char temp = stack.pop();
                        
                        if ((temp == '(' && s.charAt(index) != ')')
                            || (temp == '[' && s.charAt(index) != ']')
                            || (temp == '{' && s.charAt(index) != '}')) {
                            flag = false;
                            break;
                        }
                       
                    } else {
                        flag = false;
                        break;
                    }
                }
            
            }
            
            if (stack.isEmpty() && flag) count++;
        
        }
        return count;                
    }

}