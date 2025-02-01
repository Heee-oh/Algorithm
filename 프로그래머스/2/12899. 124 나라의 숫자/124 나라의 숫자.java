class Solution {
    int[] dn = {1,2,4};
    
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
    
        
        while (n > 0) {
            int idx = n % 3;
            if (idx == 0) idx = 3;
            
            sb.append(dn[idx - 1]);
            if (idx == 3) {
                n /= 3;
                n--;
            } else {
                n /= 3;
            }
            
            
        }

        return sb.reverse().toString();
    }
}