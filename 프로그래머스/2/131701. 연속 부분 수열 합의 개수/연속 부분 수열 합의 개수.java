import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        //Set을 이용해서 중복값 제거 및 개수 합
        HashSet<Integer> set = new HashSet<>();
        int[] tempSum = new int[elements.length];
        
        // set 처음 초기화
        for (int e : elements) 
            set.add(e);
        
        // 초기 길이 2 초기ㅘ
        int index = elements.length;
        for (int i = 0; i < index; i++) {
            tempSum[i] = elements[i] + elements[(i+1) % index];
            set.add(tempSum[i]);

        }
        // %연산을 이용해서 수열 길이 배열에 적용
        int arrindex = 0;
        
        // 길이 3~n까지 
        for (int i = 2; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                tempSum[j] += elements[(i + j) % index];
                set.add(tempSum[j]);
            }

        }
        
        return set.size();
    }
}