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
        
        // 영역이 1칸으로 분할되었으면 처리
        if (size == 1) {
            int idx = arr[r][c];
            count[idx]++;
            return;
        }
        
        // 시작점의 값을 저장
        int first = arr[r][c];
        boolean check = true; //S 내부의 모든 수가 같은지 확인용
        int div = size / 2;
        
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c+ size; j++) {
                // 하나라도 다르다면 이영역은 4등분해야함
                if (first != arr[i][j]) {
                    check = false; 
                    break;
                }
            }
        }
        
        if (check) {
            count[first]++;
            return;
        }
        
        divAndConquer(r, c, div, arr);
        divAndConquer(r + div, c, div, arr);
        divAndConquer(r,c + div, div, arr);
        divAndConquer(r + div, c + div, div,arr);    
    }
}