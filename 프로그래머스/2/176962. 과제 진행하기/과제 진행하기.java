import java.util.*;

class Solution {
    public static String[] solution(String[][] plans) {

        // 시작 시간 순으로 정렬
        Arrays.sort(plans, (o1, o2) -> o1[1].compareTo(o2[1]));
        Queue<Assignment> newAssignment = new LinkedList<>();
        Stack<Assignment> stopAssignment = new Stack<>();
        String[] answer = new String[plans.length];
        boolean check = false;
        int idx = 0;
        
        // 새 과제 큐에 넣기
        for (String[] plan : plans) {
            newAssignment.add(new Assignment(plan[0], plan[1], plan[2]));
        }

        Assignment current = newAssignment.poll();


        while (!newAssignment.isEmpty()) {
            check = false;
            Assignment newAssign = newAssignment.peek();
            int remainingTime = current.getCompleteTime() - newAssign.time;
            
            // 여유시간이 남는다면
            if (remainingTime < 0) {
                answer[idx++] = current.name; // 현재 과제는 완료 처리 
                
                if (!stopAssignment.isEmpty()) {
                    Assignment pop = stopAssignment.pop();
                    pop.time = current.getCompleteTime();
                    current = pop;
                    continue;
                }

                current = newAssignment.poll();
                check = true;
                
                // 과제 중간에 새 작업 시작 시간이라면
            } else if (remainingTime > 0) {
                // 현재 과제를 멈춤 과제 스택에 저장
                current.changePlayTime(remainingTime);
                stopAssignment.push(current);
                
                // 새 작업 꺼내기
                current = newAssignment.poll();
                check = true;
            } else {
                answer[idx++] = current.name;
                current = newAssignment.poll();
                check = true;
            }
        }

        
        // 현재 과제가 완료처리되지 않고 끝난경우 따로 처리
        if (check) {
            answer[idx++] = current.name;
        }

        while (!stopAssignment.isEmpty()) {
            answer[idx++] = stopAssignment.pop().name;
        }
        
        return answer;

    }


    static class Assignment {
        String name;
        int time;
        int playtime;

        public Assignment(String name, String time, String playtime) {
            this.name = name;
            this.time = calcTime(time);
            this.playtime = Integer.parseInt(playtime);
        }


        private int calcTime(String time) {
            String[] times = time.split(":");
            return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
        }
        
        public void changePlayTime(int playTime) {
            this.playtime = playTime;
        }
        
        public int getCompleteTime() {
            return time + playtime;
        }
    }
}