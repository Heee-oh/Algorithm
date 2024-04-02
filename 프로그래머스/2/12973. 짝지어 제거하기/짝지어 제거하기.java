import java.util.Stack;

class Solution {
    // 문자열 소문자, 같은 알파벳 2개 붙어 있는 짝 찾기
    // 짝 제거 한 뒤 앞 뒤로 문자열 이어 붙이기
    // 반복해서 모두 제거한다면 종료
    // 성공 : 1 실패 : 0
    
    
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        char[] cArray = s.toCharArray();
        
        for(char c : cArray) {
            if (!stack.isEmpty()) {
                if (stack.peek() == c)
                    stack.pop();
                else 
                    stack.push(c);
                    
            } else {
                stack.push(c);
            }
        }
        
        return stack.empty() ? 1 : 0;
    }
}