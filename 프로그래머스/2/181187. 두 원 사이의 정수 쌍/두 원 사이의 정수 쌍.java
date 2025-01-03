class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long r1Length = (long)r1 * r1;
        long r2Length = (long)r2 * r2;

        for(int x = 1; x < r2; x++) {
            int r1y = (int)Math.ceil(Math.sqrt(r1Length - (long)x * x));
            int r2y = (int)Math.floor(Math.sqrt(r2Length - (long)x * x));

            r1y = (r1y == 0) ? 1 : r1y;
            answer += r2y - r1y + 1;


        }
        return answer * 4 + (r2 - r1 + 1) * 4;
    }
}