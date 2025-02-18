class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;

        return calcMeetCount(endTime) - calcMeetCount(startTime) 
            + ((h1 % 12 + m1 + s1 == 0) ? 1 : 0);
    }
    
    public int calcMeetCount(int time) {
        
        int hs = (hourSecondMeet() * time / 43200);
        int ms = (minuteSecondMeet() * time / 3600);
        
        return hs + ms - duple(time);
    }
    
    public int duple(int time) {
        
        return time >= 43200 ? 2 : 1;
    }
    
    public int minuteSecondMeet() {
        
        // 6t = 0.1t + 360k (k는 만나는 횟수)
        double t = (double)360 / 5.9;
        
        // 분침이 1바퀴(1시간) 도는데 걸리는 시간 / t시간 후 만남
        return (int)(3600 / t);
    }
    
     public int hourSecondMeet() {
        
        // 6t = 0.0083t + 360k (k는 만나는 횟수)
        double t = (double)360 / 5.9917;
        
         // 12 * 3600
         // 시계가 1바퀴 도는데 걸리는 시간(초) / t시간 후 만남
        return (int)(43200 / t);
    }
}