import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int count = 0;
        int back = n - 1;
        int front = 0;

        while (front < back) {
            // 재료 합
            int tmp = arr[front] + arr[back];

            if (tmp == m) {
                count++;
                back--;
                front++;
            }else if (tmp < m) {
                front++;
            }else {
                back--;
            }
        }



        

        bw.write(count + "");
        bw.flush();
        bw.close();
    }


}