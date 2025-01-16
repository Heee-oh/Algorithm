import java.util.Arrays;

class Solution {
    // 무게 기준 오름차순 정렬
    // 투 포인터 혹은 deque를 사용
    // 앞 뒤 포인터의 무게 합이 limit을 넘으면 뒤 포인터는 하나의 구멍보트에 태움
        // 뒤 포인터를 앞으로 한칸 당김
    // 앞 뒤 포인터의 무게 합이 같거나 작다면 구명보트 하나 추가하고 앞뒤 포인터 앞뒤로 당김
    // 뒤 포인터 <= 앞 이면 구명보트 +1 하고 멈춤
    
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        int front = 0, back = people.length - 1;
        
        // 구명 보트 체크
        while (front < back) {
            
            int sum = people[front] + people[back];
            
            if (sum > limit) {
                back--;
                
            } else {
                front++;
                back--;
            }
            
            answer++;
        }
        
        
        return front == back ? answer + 1: answer;
    }
}