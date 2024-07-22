import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] buildings = new int[n+1];




        // 각 빌딩과 빌딩 사이의 기울기를 구한 다음
        // 최대값을 기록하여 최대값과 같거나 작으면 볼 수 없는 빌딩

        for (int i = 1; i <= n; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, countBuildings(buildings, i));

        }

        System.out.println(max);
    }


    private static int countBuildings(int[] arr, int num) {
        double gradient = -1000000001;
        int count = 0;
        for (double j = num + 1; j <arr.length; j++) {
            double m = ((arr[(int)j] - arr[num]) / (j - num));

            if (gradient < m) {
                count++;
                gradient = m;
            }
        }

        gradient = 1000000001;
        for (double j = num - 1 ; j >= 1; j--) {
            double m = (arr[(int) j] - arr[num]) / (j - num);

            if (gradient > m) {
                count++;
                gradient = m;
            }
        }

        return count;
    }


}