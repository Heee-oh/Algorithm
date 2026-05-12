class Solution {
    public int minimumEffort(int[][] tasks) {
        
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        int s = 1, e = Arrays.stream(tasks).mapToInt(x -> x[1]).sum();

        while (s < e) {
            int mid = (s + e) >>> 1;

            if (verify(mid, tasks)) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }

        return s;
    }


    private boolean verify(int energy, int[][] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            if (energy < tasks[i][1]) return false;
            energy -= tasks[i][0];
        }

        return energy >= 0;
    }
}