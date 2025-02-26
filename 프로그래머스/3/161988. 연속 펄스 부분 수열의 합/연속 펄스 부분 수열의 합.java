class Solution {
    public long solution(int[] sequence) {
        long answer = 0;

        long[] pulse1 = {1, -1};
        long[] pulse2 = {-1, 1};
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        answer = Math.max(answer, calcPartSequenceSum(sequence, pulse1, min, max));
        answer = Math.max(answer, calcPartSequenceSum(sequence, pulse2, min, max));

        return answer;
    }

    private long calcPartSequenceSum(int[] sequence, long[] pulse, long min, long max) {
        long sum = 0;

        for (int i = 0; i < sequence.length; i++) {
            sum += sequence[i] * pulse[i % 2];
            min = Math.min(min, sum);
            max = Math.max(max, sum);
        }

        // min을 뺐을때와 안뺐을때 둘중 가장 큰 것을 고려
        if (max - min < max) {
            return max;
        }
        
        return max - min;
    }
}