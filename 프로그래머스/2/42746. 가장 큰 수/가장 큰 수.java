import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        
        String[] changeNumber = new String[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            changeNumber[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(changeNumber, (a, b) -> (b + a).compareTo(a + b));
        
        for (int i = 0; i < changeNumber.length; i++) {
            sb.append(changeNumber[i]);
        }
        
        if (sb.charAt(0) == '0') {
            while(sb.charAt(0) == '0' && sb.length() > 1) {
                sb.deleteCharAt(0);
            }
        }
        
        return sb.toString();
    }
}