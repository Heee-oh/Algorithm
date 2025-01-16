import java.util.Stack;
import java.util.Arrays;

class Solution {

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        
        Arrays.fill(answer, -1);
        
        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty()) {
                int idx = stack.peek();
                
                if ( numbers[idx] < numbers[i]) {
                    answer[idx] = numbers[i];
                    stack.pop();
                    
                } else {
                   break;
                }
            }
            stack.push(i);
        }
        
        return answer;
    }
}