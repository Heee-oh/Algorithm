import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        
        //Set을 이용해서 중복값 제거 및 개수 합
        HashSet<Integer> set = new HashSet<>();
        int[] dp = new int[elements.length];

        // 길이 3~n까지 
        for (int i = 1; i <= elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                dp[j] += elements[(i + j - 1) % elements.length];
                set.add(dp[j]);
            }

        }
        
        return set.size();
    }
}