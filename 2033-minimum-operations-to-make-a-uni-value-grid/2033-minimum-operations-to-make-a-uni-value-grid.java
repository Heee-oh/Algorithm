class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int[] arr = new int[m * n];
        int rest = grid[0][0] % x;
        int idx = 0;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] % x != rest) {
                    return -1;
                }

                arr[idx++] = grid[r][c];
            }
        }

        Arrays.sort(arr);
        int target = arr[(m * n) / 2];
        
        int answer = 0;
        for (int val : arr) {
            answer += Math.abs(val - target) / x;
        }

        return answer;
    }
}