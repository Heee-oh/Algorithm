import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> clothesMap = new HashMap<>();

        for (String[] clothesType : clothes) {
            int count = clothesMap.getOrDefault(clothesType[1], 0) + 1;
            clothesMap.put(clothesType[1], count);
        }
        
        int answer = 1;
        for (Integer cnt : clothesMap.values()) {
            answer *= cnt + 1;
        }

        return answer - 1;
    }
}
