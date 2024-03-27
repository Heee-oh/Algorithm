import java.util.Stack;

class Solution {
    boolean solution(String s) {
        char[] brackets = s.toCharArray();
        
        Stack<Character> stack = new Stack<>();
        
        for (char bracket : brackets) {
            if (bracket == '(')
                stack.push(bracket);
            else 
                if (stack.isEmpty())
                    return false;
                else 
                    stack.pop();
        }

        return stack.isEmpty() ? true : false;
    }
}