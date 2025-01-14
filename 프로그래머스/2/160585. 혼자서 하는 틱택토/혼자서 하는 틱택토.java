class Solution {
    
    // 나올 수 없는 상황
    // O의 개수 - 2 >= X 개수 
    // O의 개수 < X 개수 
    // O가 빙고했는데 X의 개수가 O와 같거나 클때
    // X가 빙고했는데 O의 개수가 클때
    // O 빙고가 우선권을 가짐
    
    public int solution(String[] board) {
        int answer = 1;
        
        // O와 X의 개수 카운트
        int countO = 0;
        int countX = 0;        
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') {
                    countO++;
                } else if (board[i].charAt(j) == 'X') {
                    countX++;
                }
            }
        }
        
        // 불가능한 개수를 가진 경우
        if (countO - 2 >= countX || ((countO < countX) && (countO > 0 || countX > 0))) {
            return 0;
        } 
        
        // 빙고였을 때 
        // 가로
        for (int i = 0; i < 3; i++) {
            int bingoO = 0;
            int bingoX = 0;
            
            for (char ox : board[i].toCharArray()) {
                if (ox == 'O') bingoO++;
                else if (ox == 'X') bingoX++;
            }
            
            if (!checkBingo(bingoO, bingoX, countO, countX)) return 0;
        }
        
        for (int i = 0; i < 3; i++) {
            int bingoO = 0;
            int bingoX = 0;
            
            for (int j = 0; j < 3; j++) {
                if (board[j].charAt(i) == 'O') {
                    bingoO++;
                } else if (board[j].charAt(i) == 'X') {
                    bingoX++;
                }
            }
            
            if (!checkBingo(bingoO, bingoX, countO, countX)) return 0;
        }
        

        // 대각선
        if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2) ||
                board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)) {
            int bingoO = 0;
            int bingoX = 0;
            
            if (board[1].charAt(1) == 'O') bingoO = 3;
            else bingoX = 3;
            
            if (!checkBingo(bingoO, bingoX, countO, countX)) return 0;
        }

        
        
    
        return answer;
    }
    
    private boolean checkBingo(int bingoO, int bingoX, int countO, int countX) {
        if (bingoO == 3) {
                if (countO <= countX) return false;
                
            } else if (bingoX == 3) {
                if (countO > countX) return false;
            }
        
        return true;
    }
}