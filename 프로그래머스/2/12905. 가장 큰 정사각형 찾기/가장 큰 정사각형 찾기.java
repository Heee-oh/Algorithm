class Solution{
    public int solution(int [][]board) {
        int answer = 0;
        boolean flag = false;
        
        for (int i = 1; i < board.length; i++) {
            flag = true;
            
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = Math.min(Math.min(board[i - 1][j], board[i][j-1]), board[i-1][j-1]) + 1;
                    
                    answer = Math.max(board[i][j], answer);
                }
            }
        }
        
        return flag ? answer * answer : 1;
    }
}