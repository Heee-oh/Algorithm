import java.util.*;


class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int count = 0;
        HashMap<String, Integer> map = new HashMap<>();
        
        // 초기 map 셋팅
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], i);
        }
        
        for (int i = 0; i < discount.length - 9 ; i++) {
            int[] tmpArr = new int[number.length];
            for (int j = 0; j < 10; j++) {
                if(map.containsKey(discount[i+j])) 
                    tmpArr[map.get(discount[i+j])]++;
            }
            
            if(checkNumber(tmpArr, number)) count++;
            
        }

        return count;
    }
    
    private boolean checkNumber(int[] arr, int[] arr2) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr2[i]) {
                return false;
            } 
        }
        return true;
    }
}