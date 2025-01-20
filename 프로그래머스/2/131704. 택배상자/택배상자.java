import java.util.Stack;

class Solution {
    
    // 상자는 1부터 n까지 순서대로 내려옴
    // 기사가 알려준 순서로 택배상자를 실어야함
    // 보조 컨테이너 벨트 : stack 구조
    // 보조써도 기사가 알려준 순서대로 못싣는다면 더이상 X
    
    public int solution(int[] order) {
        
        Stack<Integer> subBelt = new Stack<>();
        int boxNum = 1, idx = 0;
        int answer = 0;
        
        while (idx < order.length) {
    
            // 알려준 순서에 맞는 택배상자 번호 전까지 보조 벨트에 옮기기
            while (boxNum < order[idx]) {
                subBelt.push(boxNum++);
            }
            
            // 순서와 메인 컨테이너벨트의 상자 번호가 같다면
            if (order[idx] == boxNum) {
                answer++;
                boxNum++;
                idx++;
                
                // 보조벨트에 있다면 
            } else if (!subBelt.isEmpty() && order[idx] == subBelt.peek()) {
                subBelt.pop();
                answer++;
                idx++;
                
                // 둘다 없다면 그만 싣는다.
            } else {
                break;
            }
        }
        
        
        return answer;
    }
}