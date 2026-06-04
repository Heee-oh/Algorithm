import java.util.*;

class Solution {
    // 자카드 유사도 = 두 집합의 교집합 크기 / 합집합 크기
    // 모두 공집합일 경우  = 1
    // 다중 집합에서 교집합은 min, 합집합은 max
    // 영문자로 된 글자 쌍만 유효!!
    // 대소문자 구분 X
    // 자카드 유사도에 65536을 곱할 것 
    public int solution(String str1, String str2) {
        int answer = 0;
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        
        boolean isAlpha1 = Character.isAlphabetic(str1.charAt(0));
        boolean isAlpha2 = Character.isAlphabetic(str2.charAt(0));;
        
        initMap(map1, str1, isAlpha1);
        initMap(map2, str2, isAlpha2);
        
        int intersection = 0, union = 0;
        
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String word = entry.getKey();
            
            // 두 집합이 같은 원소를 가질 경우 
            if (map2.containsKey(word)) {
                intersection += Math.min(entry.getValue(), map2.get(word));
                union += Math.max(entry.getValue(), map2.get(word));
                map2.remove(word); // 두번째 집합에서 이미 계산한 걸 뺀다. 
            } else {
                union += entry.getValue(); // 두번째 집합에 없는 첫번째이므로 합집합에만 연산 
            }
            
        }
        
        // 첫 번째 집합과의 교집합은 이미 계산했으므로 나머지 합집합만 구함
        for (int cnt : map2.values()) {
            union += cnt;
        }
        
        // 두 집합 모두 공집합일 경우 1 반환
        if (intersection == 0 && union == 0) {
            return 65536;
        }
        
        return (intersection * 65536) / union;
    }
    
    private void initMap(Map<String, Integer> map, String str, boolean isPrevAlpha) {
        for (int i = 1; i < str.length(); i++) {
            boolean isAlpha = Character.isAlphabetic(str.charAt(i));
            
            if (isPrevAlpha && isAlpha) {
                String word = str.substring(i-1, i+1).toLowerCase();
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            
            isPrevAlpha = isAlpha;
        }
    }
}