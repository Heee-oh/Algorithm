import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public int maxDistance(int[] colors) {
        int answer = 0;
       int[] numLeft = new int[101];
       int[] numRight = new int[101];

        Arrays.fill(numLeft, -1);
        Arrays.fill(numRight, -1);
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < colors.length; i++) {
            int c = colors[i];
            set.add(c);

            if (numLeft[c] == -1) {
                numLeft[c] = i;
            }

            numRight[c] = i;
        }


        for (int i = 0; i < colors.length; i++) {
            int c = colors[i];
            
            for (int diffColor : set) {
                if (c == diffColor) continue;

                answer = Math.max(answer, Math.abs(i - numLeft[diffColor]));
                answer = Math.max(answer, Math.abs(i - numRight[diffColor]));
            }

        }
        return answer;
    }
}