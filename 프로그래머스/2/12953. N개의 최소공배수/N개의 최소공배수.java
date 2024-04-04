import java.util.Arrays;

class Solution {
    public int solution(int[] arr) {
        Arrays.sort(arr);
        int num1 = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            int num2 = arr[i];
            
            num1 = (num1 * num2) / gcd(num2 , num1); 
        }

        
        return num1;
    }
    
    private int gcd(int a, int b) {
        
        if (b == 0) {
            return a;
        }
        
        return gcd(b, a % b);
    }
}