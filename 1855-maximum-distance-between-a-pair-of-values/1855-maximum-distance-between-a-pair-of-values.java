class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int answer = 0;

        for (int i = 0; i < nums1.length; i++) {
            int target = nums1[i];

            int s = i, e = nums2.length - 1;
            int rs = 0;

            while (s <= e) {
                int mid = (s + e) >>> 1;

                if (target <= nums2[mid]) {
                    rs = mid;
                    s = mid + 1;

                } else {
                    e = mid - 1;
                }
            }

            answer = Math.max(answer, rs - i);
            
        }

        return answer;
    }
}