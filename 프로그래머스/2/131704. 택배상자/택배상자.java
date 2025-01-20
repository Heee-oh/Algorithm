import java.util.Stack;

class Solution {
    
    // 상자는 1부터 n까지 순서대로 내려옴
    // 기사가 알려준 순서로 택배상자를 실어야함
    // 보조 컨테이너 벨트 : stack 구조
    // 보조써도 기사가 알려준 순서대로 못싣는다면 더이상 X
    
    public int solution(int[] order) {
        
        Stack<Integer> subBelt = new Stack<>();
        int answer = 0;
        int boxNum = 1;
        int idx = 0;
        
        while (boxNum <= order.length || !subBelt.isEmpty()) {
            
            if (subBelt.size() > order.length) break;
            
            // 순서와 메인 컨테이너벨트의 상자 번호가 같다면
            if (order[idx] == boxNum) {
                answer++;
                idx++;
                boxNum++;
                
                // 보조벨트에 있다면 
            } else if (!subBelt.isEmpty() && order[idx] == subBelt.peek()) {
                answer++;
                idx++;
                subBelt.pop();
                
            } else {
                
                do {
                  subBelt.push(boxNum);
                }while (boxNum++ < order[idx]);
                
            }
            
        }
        
        
        return answer;
    }
}