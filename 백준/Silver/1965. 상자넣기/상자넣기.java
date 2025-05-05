import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n];
        int idx = 0;


        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());


            if (idx == 0 || seq[idx - 1] < num) {
                seq[idx++] = num;
                continue;
            }

            int left = 0, right = idx - 1;

            while (left < right) {

                int mid = (left + right) >>> 1;

                if (seq[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            seq[left] = num;
        }

        System.out.println(idx);

    }

}