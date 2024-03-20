class Solution {
    public int solution(String[][] board, int h, int w) {
        int[] dh = {0,0,1,-1};
        int[] dw = {1,-1,0,0};
        int count = 0;
        
        String currentColor = board[h][w];
        
        for (int i = 0; i < 4; i++) {
            
            if (h + dh[i] >= board.length || w + dw[i] >= board[0].length || h + dh[i] < 0 || w + dw[i] < 0) {
                continue;
            } else {
                if (board[ h+dh[i] ][ w+dw[i] ].equals(currentColor)) {
                    count++;
                }
            }
        }
        
        return count;
    }
}