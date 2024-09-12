import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> goodNum = new HashSet<>();

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            int value = map.getOrDefault(arr[i], 0) + 1;
            map.put(arr[i], value);
        }


        // 정렬하여 큰 값과 작은 값의 합을 구하기 위함
        Arrays.sort(arr);

        int count = 0;

        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = n - 1;

            while (start < end) {

                int sum = arr[end] + arr[start];
                if (start == i) start++;
                else if (end == i) end--;

                else {

                    if (sum == arr[i] ) {
                        count++;
                        break;

                    } else {
                        if (sum < arr[i]) {
                            start++;
                        }else {
                            end--;
                        }
                    }
                }

            }

        }


        bw.write(count + "");
        bw.flush();
        bw.close();
    }


}