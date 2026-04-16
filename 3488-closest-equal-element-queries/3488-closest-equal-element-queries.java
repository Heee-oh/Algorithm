import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        List<Integer> answer = new ArrayList<>();
        List<Integer>[] num = new List[1000001];

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];

            if (num[n] == null) {
                num[n] = new ArrayList<>();
            }

            num[n].add(i);

        }


        // 이분탐색으로 
        
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            int qn = nums[q];
            int size = num[qn].size();

            if (num[qn].size() == 1) {
                answer.add(-1);
                continue;
            }

            // 해당 원소의 값들 중에서 queries idx가 있는 곳을 이분탐색
            int s = 0, e = num[qn].size() - 1;
            int find = 0;
            while (s <= e) {
                int mid = (s + e) >>> 1;
                
                // 인덱스 비교 
                if (q >= num[qn].get(mid)) {
                    find = mid;
                    s = mid + 1;
                    
                } else {
                    e = mid - 1;
                }
            }
            
            // 현재 queries[i]에 해당하는 원소의 idx
            int base = num[qn].get(find); 
            int rs;

            if (find == 0) {
                rs = Math.min(num[qn].get(find+1) - base, base - num[qn].get(size - 1) + nums.length);                
            } else if (find == size - 1) {
                rs = Math.min(num[qn].get(0) - base + nums.length, base - num[qn].get(find-1));
                
            } else {
                rs = Math.min(base - num[qn].get(find - 1), num[qn].get(find+1) - base);
            }
      
            answer.add(rs);

        }


      
        
        return answer;
    }
}