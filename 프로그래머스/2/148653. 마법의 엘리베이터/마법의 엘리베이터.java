class Solution {
    // 현재 층수 + 버튼 수로 이동
    // 0보다 작으면 안움직임
    // 0층이 가장 아래층\
    
    // 마법의 돌 1개 소요
    // 탐욕법
    // 10^c 이며 C는 0이상 정수
    
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            
            int tmp = storey % 10;
            storey /= 10;
            
            if (tmp > 5 || tmp == 5 && storey % 10 >= 5) {
                answer += 10 - tmp;
                storey += 1;
                
            } else {
                answer += tmp;
                
            }
        }
        
        return answer;
    }
}