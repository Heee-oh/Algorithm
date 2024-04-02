class Solution {
    public int solution(int n) {
        int[] fibonacci = new int[100001];
        
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        
        for (int i = 2; i < fibonacci.length; i++) {
            fibonacci[i] = (fibonacci[i-1] + fibonacci[i-2]) % 1234567;
            if (i == n) 
                break;    
        }
        
        return fibonacci[n];
    }
}