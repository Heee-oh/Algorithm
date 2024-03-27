import java.util.*;

class Solution {
    public String solution(String s) {
        String[] arr = s.split(" ");
        Arrays.sort(arr, (o1, o2) -> Integer.parseInt(o1) - Integer.parseInt(o2));
        
        return arr[0] + " " + arr[arr.length -1 ];
    }
}