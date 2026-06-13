import java.util.*;
import java.util.stream.IntStream;

class Solution {
    // 절대값이 가능하므로 최대 최솟값 구하기
    int[] parent;
    String[] patterns = {"+-*", "+*-", "-+*", "-*+", "*-+", "*+-"};
    
    public long solution(String expression) {
        long answer = 0;
        
        long [] nums = Arrays.stream(expression
                                      .replaceAll("[^0-9]", " ")
                                      .split(" ")
                                     )
            .mapToLong(Long::parseLong)
            .toArray();
        
        String[] tmpOp = expression.replaceAll("[0-9]", "").split("");
        char[] op = new char[tmpOp.length];
        
        // map으로 연산자 인덱스화 
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < tmpOp.length; i++) {
            op[i] = tmpOp[i].charAt(0);
            map.computeIfAbsent(op[i], k -> new ArrayList<>()).add(i);
        }
        
        
        long[] origin = nums.clone(); // 원본값 저장 
        // 각 패턴에 대하여 탐색
        for (String pattern : patterns) {
            parent = IntStream.range(0, nums.length).toArray();
            nums = origin.clone();
            
            for (char c : pattern.toCharArray()) {
                if (!map.containsKey(c)) continue;
                
                for (int idx : map.get(c)) {
                    // 각 숫자의 집합을 찾음
                    int a = find(idx); 
                    int b = find(idx+1);
                    
                    if (c == '+') {
                        nums[a] += nums[b];
                        
                    } else if (c == '-') {
                        nums[a] -= nums[b];
                        
                    } else {
                        nums[a] *= nums[b];
                    }
                    
                    // 작은 집합으로 합침 
                    union(a, b);
                }
            }
            
            answer = Math.max(answer, Math.abs(nums[0]));
            
        }
        
        return answer;
    }
    
    // 각 숫자를 유니온파인트셋으로 가정하고 빠른 연산 수행
    private void union(int a, int b) {
        
        a = find(a);
        b = find(b);
        
        if (a <= b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
    
    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}

