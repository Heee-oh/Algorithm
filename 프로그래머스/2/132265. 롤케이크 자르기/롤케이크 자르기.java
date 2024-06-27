import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        HashSet<Integer> cheolsuSet = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int point = 0;
        
        if (topping.length == 1) return 0;

        
        for (int topp : topping) {
            int broTopping = map.getOrDefault(topp, 0) + 1;
            map.put(topp,broTopping);
        }

        for (int i = 0; i < topping.length ; i++) {
            
            cheolsuSet.add(topping[i]);
            
            int broTopping = map.getOrDefault(topping[i], 0);
            
            if (broTopping == 0 || broTopping == 1) {
                map.remove(topping[i]);
            } else {
                map.put(topping[i], broTopping - 1);
            }
            
            if (cheolsuSet.size() == map.size()) answer++;
        }
        
        
        
        return answer;
    }
}

// 1 2 3  1 4 2 1
// 1  2 3 1 4 2 1
// 1 2  3 1 4 2 1
// 1 2 3  1 4 2 1

// 철수 set 만들기

// 동생 set 만들기
