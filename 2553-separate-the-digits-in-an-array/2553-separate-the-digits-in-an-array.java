class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            
            for (char n : String.valueOf(nums[i]).toCharArray()) {
                list.add(n - '0');
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}