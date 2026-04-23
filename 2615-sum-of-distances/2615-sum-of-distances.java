class Solution {
    public long[] distance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        long[] answer = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.computeIfAbsent(nums[i], k -> new ArrayList<>());
            list.add(i);

        }

        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            int n = list.size();
      

            // 인덱스들의 총합 계산
            long totalSum = 0;
            for (int index : list) {
                totalSum += index;
            }

   
            // 현재 인덱스 기준 왼쪽 오른쪽 합들에 대하여 현재 인덱스를 다 빼서 더한 값이 거리값
            long leftSum = 0;
            for (int i = 0; i < n; i++) {
                long currentIdx = list.get(i);
                
                // 오른쪽 인덱스들의 합 = 전체합 - 현재까지의 왼쪽합 - 현재 인덱스
                long rightSum = totalSum - leftSum - currentIdx;

                // 왼쪽 거리 합: (현재 인덱스 * 왼쪽 개수) - 왼쪽 인덱스들의 합
                long leftDist = (currentIdx * i) - leftSum;
                // 오른쪽 거리 합: 오른쪽 인덱스들의 합 - (현재 인덱스 * 오른쪽 개수)
                long rightDist = rightSum - (currentIdx * (n - 1 - i));

                answer[(int)currentIdx] = leftDist + rightDist;

                
                leftSum += currentIdx;
            }
        }

        return answer;
    }
}