import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;



class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];

        ArrayList<Integer> list = new ArrayList<>();
        // keymap의 각 문자에 대한 인덱스를 map에 추가 
        Map<Character, Integer> map = new HashMap<>();

        for(String s : keymap) {
            for(int i = 0; i < s.length(); i++) {
                char tmp = s.charAt(i);
                int value = map.getOrDefault(tmp, 200);
                //System.out.println(tmp + " " + (i+1));
                if(value > i + 1) {
                    map.put(tmp, (i + 1));
                }
            }
        }

        // targets의 각 문자를 map.get으로 넣어서 값을 더한다
        int index = 0;
        for(String str : targets) {
            int count = 0;
            for(char c : str.toCharArray()) {
                if(map.getOrDefault(c, -1) == -1) {
                    count = -1;
                    break;
                } else {
                    count += map.getOrDefault(c, -1);
                }

            }
            answer[index++] = count;
        }

        
        
        return answer;
    }
}