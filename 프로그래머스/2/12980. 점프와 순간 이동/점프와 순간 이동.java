import java.util.*;

// k 칸 점프 : 건전지 k 사용
// 현재 까지 거리 x 2 순간이동 : 건전지 사용 x 
// 탐욕법인듯? 사용량 최소화로 n까지 가기

// current : 현재 거리
// 5 -1 = 4 /2 = 2 / 2 = 1   k = 1
// 6 / 2 = 3 -1 = 2 /2 = 1   k = 1
// 5000 / 2 = 2500 / 2 = 1250 / 2 = 625  - 1 = 624 /2 = 312 / 2 = 39 -1 = 38 /2 = 19 -1 = 18 / 2 = 9 -1 = 8 /2 = 4/ 2 = 2/ 2= 1


public class Solution {
    public int solution(int n) {
        int count = 0;
        
        while(n > 0) {
            if (n % 2 == 0) {
                n /= 2;
                
            } else {
                n -= 1;
                count++;
            }
        }

        return count;
    }
}