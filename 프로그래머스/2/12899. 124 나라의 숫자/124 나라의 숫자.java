class Solution {
    int[] dn = {1,2,4}; // 124 나라 변환 배열
    
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
    
        while (n > 0) {
            int idx = n % 3; // 3으로 나눈 나머지가 124나라의 변환 인덱스이다.
            if (idx == 0) {
                idx = 3;
                n--;
            }
            
            sb.append(dn[idx - 1]);
            n /= 3;
        }

        return sb.reverse().toString();
    }
}