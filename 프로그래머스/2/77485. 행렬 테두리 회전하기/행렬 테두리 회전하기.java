import java.util.*;

class Solution {
    
    // 좌,하,우,상
    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] map = new int[rows + 1][columns + 1];
        
        
        // 1 ~ rows * columns 까지 숫자 채우기
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                map[r][c] = ((r - 1) * columns) + c;
            }
        }
        
        // queries들을 처리
        // 가장 작은 숫자들을 담을 배열
        int[] answer = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            int y1 = queries[i][0];
            int x1 = queries[i][1];
            int y2 = queries[i][2];
            int x2 = queries[i][3];
            
            int[] current = {y1, x1}; // 현재 위치 
            int preValue = map[y1][x1]; // 회전시 넣을 값
            answer[i] = preValue; // 최솟값 찾기
            
            
            for (int j = 0; j < 4; j++) {
                int limit = (j % 2 == 0) ? x2 - x1 : y2 - y1;
                
                for (int k = 0; k < limit; k++) {
                    int newR = current[0] + dy[j];
                    int newC = current[1] + dx[j];
                    
                    int tmp = map[newR][newC]; // 기존 값 빼고
                    map[newR][newC] = preValue; // 이전 값 넣기
                    preValue = tmp;
                    
                    current[0] = newR;
                    current[1] = newC;
                    // 최솟값 넣기
                    answer[i] = Math.min(answer[i], map[newR][newC]);
                }
            }
            
        }
        
        
        return answer;
    }
}