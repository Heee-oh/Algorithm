import java.util.*;

class Solution {
    // TreeSet은 중복값 제거라 사용불가
    // 2개의 우선순위큐? 너무 효율이 안좋음

    // 
    public int[] solution(String[] operations) {
        ArrayList<Integer> list = new ArrayList<>();
        
        int min = 0; 
        for (int i = 0; i < operations.length; i++) {
            String[] op = operations[i].split(" ");
            operate(op, list);
        }


        if (list.isEmpty()) {
            return new int[] {0,0};
        }
        
         

        return new int[]{list.get(list.size() - 1), list.get(0)};
    }

    private void operate(String[] op, ArrayList<Integer> list) {
        if (op[0].equals("I")) {
            list.add(Integer.parseInt(op[1]));
            Collections.sort(list);
        } else if (op[0].equals("D") 
                   && op[1].equals("1")
                   && !list.isEmpty()) { 
            
            list.remove(list.size() - 1);
            
        } else if (!list.isEmpty()){
            
            list.remove(0);
        }
    }
}