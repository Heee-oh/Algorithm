class Solution {
    boolean[] row, col;
    int size, answer;
    Queen[] arr1, arr2;
    int len1, len2;
    
    
    class Queen {
        int r, c;
        
        public Queen(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        public boolean valid(int or, int oc) {
            return Math.abs(r - or) == Math.abs(c - oc);
        }
    }
    // 행, 열, r,c 간격이 동일 
    public int solution(int n) {
        answer = 0;
        size = n;
        
        arr1 = new Queen[n];
        arr2 = new Queen[n];
        len1 = 0; len2 = 0;
        
        row = new boolean[n];
        col = new boolean[n];
        
        dfs(0, 0, 0);
        
        return answer;
    }
    
    
    private void dfs(int r, int c, int cnt) {
        if (cnt == size) {
            answer++;
            return;
        }
        
        if (r >= size || c >= size) {
            return;
        }
        
        for (int j = 0; j < size; j++) {
                // 1. 같은 행, 열 제외
                if (row[r] || col[j]) continue; 
                
                // 2. 같은 대각 제외 
                if (((r + j) & 1) == 1) {
                    boolean flag = true;
                    for (int k = 0; k < len1; k++) {
                        if (arr1[k].valid(r, j)) {
                            flag = false;
                            break;
                        }
                    }
                
                    if (!flag) continue;
                    arr1[len1] = new Queen(r, j);
                    len1++;
                    row[r] = col[j] = true;
                    dfs(r + 1, j, cnt + 1);
                    len1--;
                    
                } else {
                    boolean flag = true;
                    for (int k = 0; k < len2; k++) {
                        if (arr2[k].valid(r, j)) {
                            flag = false;
                            break;
                        }
                    }
                
                    if (!flag) continue;
                    
                    arr2[len2] = new Queen(r, j);
                    len2++;
                    row[r] = col[j] = true;
                    dfs(r + 1, j, cnt + 1);
                    len2--;
                }
    
                
                row[r] = col[j] = false;
            
            }
             
        }

}