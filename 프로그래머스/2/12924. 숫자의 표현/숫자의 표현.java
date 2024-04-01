class Solution {
    public int solution(int n) {
        
        if( n < 3) return 1;
        
        int count = 0;
        int temp = 0;
        
        for (int i = 1; i < n + 1; i++ ) {
            for (int j = i; j < n + 2; j++) {
                
                if (temp == n) {
                    count++;
                    temp = 0;
                    break;
                } else if (temp > n) {
                    temp = 0;
                    break;
                } else {                  
                    temp += j;
                }
            }
            
        }
        
        return count;
    }
}