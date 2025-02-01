import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 최대 룸 개수, 시-분 
        Integer[] time = new Integer[1000];
        // 시작 시간 순 정렬
        Arrays.sort(book_time, (o1, o2) -> o1[0].compareTo(o2[0]));
        int answer = 0;
        
        for (String[] checkOut : book_time) {
            String[] start = checkOut[0].split(":");
            String[] end = checkOut[1].split(":");
            
            // 분으로 계산
            int startTime = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int endTime = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
            
            for (int i = 0; i < time.length; i++) {
                // 빈 방이라면
                if (time[i] == null) {
                    time[i] = (endTime + 10) ; // 예약 시각이 자정을 넘기는 경우는 없음
                    answer++;
                    break;
                    
                    // 방이 채워져 있고, 예약시간이 체크아웃 후 시간이라면
                } else if (time[i] <= startTime) {
                    time[i] = (endTime + 10) ;
                    break;
                }
                // 현재 방이 이미 예약자가 있고, 체크아웃 전 시간이라면 다음 방으로 이동
            }
        }
        
        return answer;
    }
}