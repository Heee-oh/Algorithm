class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;

        boolean[][] chk = new boolean[n + 1][n + 1];

        for(int i = 0; i < results.length; i++) {
            chk[results[i][0]][results[i][1]] = true;
        }

        for(int k = 1; k < n + 1; k++) {
            for(int i = 1; i < n + 1; i++) {
                for(int j = 1; j < n + 1; j++) {
                    if(i != j && chk[i][k] && chk[k][j]) {
                        chk[i][j] = true;
                    }
                }
            }
        }

        for(int i = 1; i < n + 1; i++) {
            boolean pass = true;
            for(int j = 1; j < n + 1; j++) {
                if(i != j && !(chk[i][j] || chk[j][i])) {
                    pass = false;
                    break;
                }
            }
            if(pass) {
                answer++;
            }
        }

        return answer;
    }
}