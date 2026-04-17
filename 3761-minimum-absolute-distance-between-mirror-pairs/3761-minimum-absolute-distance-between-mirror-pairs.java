import java.util.*;

class Solution {


    public int minMirrorPairDistance(int[] nums) {
        int answer = Integer.MAX_VALUE;
        

        Map<Integer, List<Integer>> map = new HashMap<>();

        // 각 값에 대한 idx를 저장
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.computeIfAbsent(nums[i], k -> new ArrayList<>());
            list.add(i);
        }

        // 각 숫자 탐색

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            // 숫자 뒤집기 
            int reverse = 0;
            int tmp = num;
            while (tmp != 0) {
                reverse *= 10;
                reverse += tmp % 10;
                tmp /= 10;
            }
            
            // 미러가 존재하지 않다면 넘어감
            if (!map.containsKey(reverse) ) continue;
            
            List<Integer> list = map.get(reverse);
            

            // 현재 탐색중인 원래 숫자의 대략적 위치를 구함
            int s = 0, e = list.size();
           while (s < e) {
                int mid = (s + e) >>> 1;
                int idx = list.get(mid);

                if (i >= idx) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
            
            // 이전이라면 이미 들렀다고 볼 수 있음
            if (list.size() <= s) continue;
            
            if (list.get(s) < i) {
                s++;
            }


            for (int j = s; j < Math.min(s + 2, list.size()); j++) {
                if (list.get(j) == i) continue;

                int idx = list.get(j);
                answer = Math.min(answer, Math.abs(i - idx));
            }

        }        
        


        return answer == Integer.MAX_VALUE ? -1 : answer;

    }
}