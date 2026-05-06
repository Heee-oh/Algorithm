class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        
        char[][] answer = new char[n][m];


        for (int r = 0; r < m; r++) {
            Queue<Integer> q = new LinkedList<>();
            
            for (int c = n-1; c >= 0; c--) {
                char state = boxGrid[r][c];
                if (state == '.') {
                    q.offer(c);

                } else if (state == '*') {
                    q.clear();

                } else if (state == '#' && !q.isEmpty()) {
                    int free = q.poll();
                    boxGrid[r][free] = '#';
                    boxGrid[r][c] = '.';
                    q.offer(c);
                }
            }
        }


        for (int c = 0; c < n; c++) {
            for (int r = 0; r < m; r++) {
                answer[c][m - 1 - r] = boxGrid[r][c];
            }
        }
        

        return answer;
    }
}