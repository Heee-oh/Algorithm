import java.util.*;
 
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int[][] arr = new int[board.length+1][board[0].length+1];
        
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            if (type == 1) {
                arr[r1][c1] += -degree;
                arr[r1][c2 + 1] += degree;
                arr[r2 + 1][c1] += degree;
                arr[r2 + 1][c2 + 1] += -degree;
                
            } else {
                arr[r1][c1] += degree;
                arr[r1][c2 + 1] += -degree;
                arr[r2 + 1][c1] += -degree;
                arr[r2 + 1][c2 + 1] += degree;
                
            }

            
        }        

        
        int pSum;
        for (int r = 0; r < arr.length; r++) {
            pSum = 0;
            for (int c = 0; c < arr[r].length; c++) {
                arr[r][c] += pSum;
                pSum = arr[r][c];
            }
        }

        for (int c = 0; c < arr[0].length; c++) {
            pSum = 0;
            for (int r = 0; r < arr.length; r++) {
                arr[r][c] += pSum;
                pSum = arr[r][c];
            }
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[0].length - 1; j++) {
                board[i][j] += arr[i][j];
                
                if (board[i][j] > 0) {
                    answer++;
                }
            }
            
        }
        
        
        return answer;
    }
}