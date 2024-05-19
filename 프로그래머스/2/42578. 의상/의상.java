import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> clothesMap = new HashMap<>();

        
        for (String[] clothesType : clothes) {
            int count = clothesMap.getOrDefault(clothesType[1], 0) + 1;
            clothesMap.put(clothesType[1], count);
        }
        
        if (clothesMap.size() == 1) {
            return clothes.length;
        }
        
        int temp = 1;
        for (Integer cnt : clothesMap.values()) {
            temp *= cnt + 1;
        }
                
        
        
        return temp - 1;
    }
}