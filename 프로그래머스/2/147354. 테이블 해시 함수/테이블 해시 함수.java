import java.util.Arrays;

class Solution {
    // 해시 함수 매개변수 col, row_begin, row_end
    // col 번째 컬럼 기준 오름차순 , 같다면 기본키(첫번째 컬럼) 기준 내림차순
    // 정렬후 S_i를 i 번째 행의 튜플에 대해 각 컬럼 값을 i로 나눈 나머지들의 합으로 정의
    // row_begin <= i <= row_end 인 모든 S_i 누적, XOR 연산 한 값 반환
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int[] S = new int[row_end - row_begin + 1];
        int answer = 0;
        
        // col 기준 오름차순, 같다면 기본키 기준 내림차순 정렬
        Arrays.sort(data, (o1, o2) -> o1[col - 1] != o2[col - 1] 
                    ? o1[col - 1] - o2[col - 1] 
                    : o2[0] - o1[0]);
        
        
        for (int i = row_begin - 1; i < row_end; i++) {
            int tmp = 0;
            for (int j = 0; j < data[i].length; j++) {
                tmp += data[i][j] % (i + 1);
            }
            answer ^= tmp;
        }
        
        return answer;
    }
}