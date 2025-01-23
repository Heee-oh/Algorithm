class Solution {
    // 분할 정복
    // 반으로 나누고 0<2 2<4
    // for문으로 해당 영역 탐색
    int[] count = new int[2];
    
    public int[] solution(int[][] arr) {
        divAndConquer(0,0,arr.length,arr);
        return count;
    }
    
    private void divAndConquer(int r, int c, int size, int[][] arr) {
        
        //S 내부의 모든 수가 같은지 확인용
        if (areaSearch(r,c,size,arr)) {
            int firstIdx = arr[r][c];
            count[firstIdx]++;
            return;
        }
        
        int div = size / 2; // 반으로 나눈다. 
        divAndConquer(r,c + div, div, arr);       // 1사분면
        divAndConquer(r, c, div, arr);            // 2사분면
        divAndConquer(r + div, c, div, arr);      // 3사분면
        divAndConquer(r + div, c + div, div,arr); // 4사분면
    }
    
    private boolean areaSearch(int r, int c, int size, int[][] target) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c+ size; j++) {
                // 하나라도 다르다면 이영역은 4등분해야함
                if (target[r][c] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}