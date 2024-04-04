class Solution {
    // 1~N까지 참가 
    // 토너먼트로 1-2, 3-4 씩 이긴사람이 또 1~N/2 까지 번호 부여
    
    // if 1-2 2승 = 1번, 3-4 3승 = 2번
    // 1명이 남을때까지 
    
    // 처음 라운드에서 A 번 과 B번이 몇 번째 라운드에서 만나나?
    // 붙기 전까지 항상 이김
    // 부전승 없음
    // A != B <= N
    

    public int solution(int n, int a, int b) {
        
        int left, right;
        // 왼쪽 오른쪽 상대 지정
        if (a > b) {
            left = b;
            right = a;
        } else {
            left = a;
            right = b;
        }
        int count = 1;
        
        while(true) {
            if (right - left == 1 && left % 2 == 1) {
                break;
            }
            
            if (left % 2 == 1)
                left = (left / 2) + 1;
            else 
                left /= 2;
            
            if (right % 2 == 1)
                right = (right /2) + 1;
            else 
                right /= 2;
            
            count++;
        }
        
    
        return count;
    }
}