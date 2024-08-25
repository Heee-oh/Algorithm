class Solution {
    int count = 0;
    boolean flag = false;
    StringBuilder sb = new StringBuilder();
    
    public int solution(String word) {
        dfs(word, 0);
        return count;
    }
    
    private void dfs(String word, int depth) {

        if (sb.toString().equals(word)) {
            flag = true;
            return;
        }

        if (depth > 4) return;
        
        for (int i = 1; i <= 5; i++) {
            if (flag) return;
            count++;
            sb.append("AEIOU".charAt(i - 1));
            dfs(word, depth+1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}