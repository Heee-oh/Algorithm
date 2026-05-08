class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int maxVal = 1000000;
        boolean[] isPrime = new boolean[maxVal + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        //소수 판별 (사용자님의 에라토스테네스의 체 유지)
        for (int i = 2; i * i <= maxVal; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= maxVal; j += i) isPrime[j] = false;
            }
        }

        // 소수의 배수들이 위치한 인덱스들을 미리 매핑 (순간이동용)
        List<Integer>[] indicesByPrime = new ArrayList[maxVal + 1];
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            // 소인수 분해를 통해 해당 숫자가 어떤 소수의 배수인지 저장
            int temp = val;
            for (int p = 2; p * p <= temp; p++) {
                if (temp % p == 0) {
                    if (indicesByPrime[p] == null) indicesByPrime[p] = new ArrayList<>();
                    indicesByPrime[p].add(i);
                    while (temp % p == 0) temp /= p;
                }
            }
            if (temp > 1) { // 마지막 남은 소인수 처리
                if (indicesByPrime[temp] == null) indicesByPrime[temp] = new ArrayList<>();
                indicesByPrime[temp].add(i);
            }
        }

        // BFS 구조로 변경
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[n]; // 인덱스 기준 거리
        Arrays.fill(dist, -1);
        boolean[] usedPrime = new boolean[maxVal + 1]; // 동일 소수 중복 탐색 방지

        q.offer(0);
        dist[0] = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == n - 1) return dist[curr];

            // 인접 이동: i+1, i-1
            int[] nexts = {curr + 1, curr - 1};
            for (int next : nexts) {
                if (next >= 0 && next < n && dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    q.offer(next);
                }
            }

            // 소수 순간이동: nums[curr]가 소수일 때만 가능
            int p = nums[curr];
            if (isPrime[p] && !usedPrime[p]) {
                usedPrime[p] = true;
                if (indicesByPrime[p] != null) {
                    for (int nextIdx : indicesByPrime[p]) {
                        if (dist[nextIdx] == -1) {
                            dist[nextIdx] = dist[curr] + 1;
                            q.offer(nextIdx);
                        }
                    }
                }
            }
        }

        return dist[n - 1];
    }
}