import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        int count = 0;
        int front = 0, back = 0;
        int sum = 0;
        while (back < n || sum >= n) {
            if (sum == n) {
                count++;
                sum -= arr[front++];

            }else if (sum < n) {

                sum += arr[back++];
            } else {

                sum -= arr[front++];
            }
        }

        

        bw.write(count + "");
        bw.flush();
        bw.close();
    }


}