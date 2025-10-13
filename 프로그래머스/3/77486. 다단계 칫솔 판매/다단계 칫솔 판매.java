import java.util.*;
 
class Solution {
    static Map<String, Integer> map = new HashMap<>();
    static Map<String, Integer> nameIdx = new HashMap<>();
    static int[] parents;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        parents = new int[enroll.length];
    
        for (int i = 0; i < enroll.length; i++) {
            nameIdx.put(enroll[i], i);
        }
        
        // 부모 설정 
        for (int i = 0; i < referral.length; i++) {
            String parent = referral[i];
            if (parent.equals("-")) {
                parents[i] = -1;
            } else {
                int parentIdx = nameIdx.get(parent);
                parents[i] = parentIdx;
            }
        }
        
        for (int i = 0; i < seller.length; i++) {

            int idx = nameIdx.get(seller[i]); // 판매자의 인덱스 찾기 
            int cost = amount[i] * 100;
            
            // center 전 까지 탐색
            while (idx != -1) {
                int benefit = cost / 10;
                int mine = cost - benefit;
                answer[idx] += mine;

                if (benefit < 1) break; // 더 이상 분배 불가
                idx = parents[idx];
                cost = benefit;
            }
            
        }
        
        
        return answer;
    }
}