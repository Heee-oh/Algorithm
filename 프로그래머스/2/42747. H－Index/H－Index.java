import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        
        if (citations.length == 1) return citations[0];
        int max = 0;
        
        for (int i = 0; i < citations[citations.length - 1]; i++) {
            int count = 0;
            for (int j = 0; j < citations.length; j++) {
                if ( i <= citations[j]) count++;   
            }
            
            if (i <= count) max = i;
            else break;
            
        }
        
        // n편중 h번 이상이 h 개 이상, 나머지는 h번 이하 인 h의 최댓값

        return max;
    }
}