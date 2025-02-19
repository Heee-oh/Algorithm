class Solution {
    // 완전탐색
    // 백트래킹
    
    boolean[] visited; 
    int min = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        char[] word = begin.toCharArray();
        dfs(word, words, target, 0);
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    
    private void dfs(char[] start, String[] words, String target, int cnt) {

        // target이면 최솟값 갱신
        if (String.valueOf(start).equals(target)) {
            min = Math.min(min, cnt);
            return;
        }
        
        
        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue;
            int alphaCnt = 0;
            
            // 한 번에 한개의 알파벳만 변경 가능
            for (int j = 0; j < words[i].length(); j++) {
                if (start[j] != words[i].charAt(j)) {
                    alphaCnt++;
                }
            }
            
            // 다음 단어 알파벳이 2개이상 또는 완전히 같은 경우 바꾸지 않음
            if (alphaCnt > 1 || alphaCnt == 0) continue;
            
            visited[i] = true;
            // 1개만 다른경우 변경
            dfs(words[i].toCharArray(), words, target, cnt + 1);
            visited[i] = false;
        }
        
    }
}