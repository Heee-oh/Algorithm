import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int right = n - 1;
        int min = Integer.MAX_VALUE;
        int[] answer = new int[2];
        while (left < right) {

            int sum = seq[left] + seq[right];
            int abs = Math.abs(sum);

            if (min > abs) {
                min = abs;
                answer[0] = seq[left];
                answer[1] = seq[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }


        System.out.println(answer[0] + " " + answer[1]);

    }



}