import java.util.*;

class Solution {
    public int solution(int[] a) {
        if (a.length <= 1) return 0;
        
        int answer = -1;
        int[] count = new int[a.length];
        int[] lastUsed = new int[a.length];
        Arrays.fill(lastUsed, -1);
        
        // 0번째를 기준으로 뒤인 1번과 다르다면 부분 수열 카운트
        if (a[0] != a[1]) {
            count[a[0]]++;
            lastUsed[a[0]] = 1; // 뒤에 요소와 연결했으므로 a[0]의 마지막 연결한 인덱스가 1
        }
        
        // a.length -1 번째는 뒤가 없으므로 따로 처리 
        for (int i = 1; i < a.length - 1; i++) {
            
            if (a[i] != a[i-1] && lastUsed[a[i]] != i -1) {
                count[a[i]]++;
                lastUsed[a[i]] = i -1;
            }else if (a[i] != a[i+1] && lastUsed[a[i]] != i + 1) {
                count[a[i]]++;
                lastUsed[a[i]] = i + 1;
            }
            
        }
        
        if (a[a.length - 2] != a[a.length - 1] && lastUsed[a[a.length - 1]] != a.length - 2) {
            count[a[a.length - 1]]++;
        }
        
        
    
        return Arrays.stream(count).max().getAsInt() * 2;
    }
}