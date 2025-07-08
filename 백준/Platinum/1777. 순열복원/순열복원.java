import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == 0) {
                list.add(i);
                continue;
            }

            int left = 0, right = list.size() - 1;
            while (left <= right) {

                int mid = (left + right) >>> 1;

                if (list.size() - mid < num) {
                    right = mid - 1;
                }  else if (list.size() - mid > num){
                    left = mid + 1;
                } else {
                    left = mid;
                    break;
                }
            }

            list.add(left, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int number : list) {
            sb.append(number).append(" ");
        }

        System.out.println(sb.toString().trim());


    }





}